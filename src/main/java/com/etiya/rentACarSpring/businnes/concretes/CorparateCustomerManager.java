package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.businnes.abstracts.AuthService;
import com.etiya.rentACarSpring.businnes.abstracts.UserService;
import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.dtos.CorparateCustomerSearchListDto;
import com.etiya.rentACarSpring.core.utilities.adapter.findexScoreServiceAdapter.findexScoreService;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.CorparateCustomerService;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.CreateCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.DeleteCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.UpdateCorparateRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CorparateCustomerDao;
import com.etiya.rentACarSpring.entities.CorparateCustomer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorparateCustomerManager implements CorparateCustomerService {

    private CorparateCustomerDao corparateCustomerDao;
    private ModelMapperService modelMapperService;
    private findexScoreService findexScoreService;
    private Environment environment;
    private LanguageWordService languageWordService;
    private AuthService authService;
    private UserService userService;


    @Autowired
    public CorparateCustomerManager(CorparateCustomerDao corparateCustomerDao, ModelMapperService modelMapperService
                    ,findexScoreService findexScoreService, Environment environment, LanguageWordService languageWordService,
                                    @Lazy AuthService authService,UserService userService) {
        super();
        this.corparateCustomerDao = corparateCustomerDao;
        this.modelMapperService = modelMapperService;
        this.findexScoreService =findexScoreService;
        this.environment = environment;
        this.languageWordService = languageWordService;
        this.authService= authService;
        this.userService=userService;
    }

    @Override
    public DataResult<List<CorparateCustomerSearchListDto>> getAll() {
        List<CorparateCustomer> result = this.corparateCustomerDao.findAll();
        List<CorparateCustomerSearchListDto> response = result.stream()
                .map(corparateCustomer -> modelMapperService.forDto().map(corparateCustomer, CorparateCustomerSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<CorparateCustomerSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.CorporateCustomerListed));
    }

    @Override
    public Result add(CreateCorparateRequest createCorparateRequest) {
        Result result = BusinnessRules.run(checkIfTaxNumberExists(createCorparateRequest.getTaxNumber()),
                authService.checkEmailIfExists(createCorparateRequest.getEmail())
        );
        if (result != null) {
            return result;
        }

        CorparateCustomer corparateCustomer = modelMapperService.forRequest().map(createCorparateRequest, CorparateCustomer.class);
        corparateCustomer.setFindexScore(findexScoreService.getIndividualFindexScore(corparateCustomer.getTaxNumber()));
        this.corparateCustomerDao.save(corparateCustomer);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CorporateCustomerAdded));
    }

    @Override
    public Result update(UpdateCorparateRequest updateCorparateRequest) {
        Result result = BusinnessRules.run( userService.existByUserId(updateCorparateRequest.getUserId())
        );
        if (result != null) {
            return result;
        }

        CorparateCustomer corparateCustomer = modelMapperService.forRequest().map(updateCorparateRequest, CorparateCustomer.class);
        corparateCustomer.setFindexScore(findexScoreService.getIndividualFindexScore(corparateCustomer.getTaxNumber()));
        this.corparateCustomerDao.save(corparateCustomer);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CorporateCustomerUpdated));
    }

    @Override
    public Result delete(DeleteCorparateRequest deleteCorparateRequest) {
        Result result = BusinnessRules.run( userService.existByUserId(deleteCorparateRequest.getCorparateCustomerId())
        );
        if (result != null) {
            return result;
        }
        this.corparateCustomerDao.deleteById(deleteCorparateRequest.getCorparateCustomerId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CorporateCustomerDeleted));
    }

    private Result checkIfTaxNumberExists(String taxNumber) {

        if (this.corparateCustomerDao.existsByTaxNumber(taxNumber)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.TaxNumberAlreadyExist));
        }
        return new SuccesResult();
    }

}
