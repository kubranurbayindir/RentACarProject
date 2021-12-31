package com.etiya.rentACarSpring.businnes.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.businnes.abstracts.AuthService;
import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.core.utilities.adapter.findexScoreServiceAdapter.findexScoreService;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.businnes.dtos.IndividualCustomerSearchListDto;

import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.rentACarSpring.entities.IndividualCustomer;


@Service
public class IndividualCustomerManager implements IndividualCustomerService {

    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;
    private findexScoreService findexScoreService;
    private Environment environment;
    private LanguageWordService languageWordService;
    private AuthService authService;

    @Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
                                     ModelMapperService modelMapperService, findexScoreService findexScoreService, Environment environment,
                                     LanguageWordService languageWordService,@Lazy AuthService authService) {
        super();
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
        this.findexScoreService = findexScoreService;
        this.environment = environment;
        this.languageWordService = languageWordService;
        this.authService=authService;
    }

    @Override
    public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
        List<IndividualCustomer> result = this.individualCustomerDao.findAll();
        List<IndividualCustomerSearchListDto> response = result.stream()
                .map(individualCustomer -> modelMapperService.forDto().map(individualCustomer, IndividualCustomerSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<IndividualCustomerSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.IndividualCustomerListed, Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result save(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        Result result = BusinnessRules.run(checkIfIdentityNumberExists(createIndividualCustomerRequest.getIdentityNumber()),
                authService.checkEmailIfExists(createIndividualCustomerRequest.getEmail())
        );
        if (result != null) {
            return result;
        }

        IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomer.setFindexScore(findexScoreService.getIndividualFindexScore(individualCustomer.getIdentityNumber()));
        this.individualCustomerDao.save(individualCustomer);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.IndividualCustomerAdded, Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        Result result = BusinnessRules.run(checkIfIdentityNumberExists(updateIndividualCustomerRequest.getIdentityNumber())
        );
        if (result != null) {
            return result;
        }
        IndividualCustomer individualCustomer = modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomer.setFindexScore(findexScoreService.getIndividualFindexScore(individualCustomer.getIdentityNumber()));
        this.individualCustomerDao.save(individualCustomer);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.IndividualCustomerUpdated, Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        this.individualCustomerDao.deleteById(deleteIndividualCustomerRequest.getIndividualCustomersId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.IndividualCustomerDeleted, Integer.parseInt(environment.getProperty("language"))));
    }

    private Result checkIfIdentityNumberExists(String identityNumber) {

        if (this.individualCustomerDao.existsByIdentityNumber(identityNumber)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.IdentityNumberAlreadyExist, Integer.parseInt(environment.getProperty("language"))));
        }
        return new SuccesResult();
    }
}
