package com.etiya.rentACarSpring.businnes.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.businnes.dtos.IndividualCustomerSearchListDto;

import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.rentACarSpring.entities.IndividualCustomer;


@Service
public class IndividualCustomerManager implements IndividualCustomerService {

    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
                                     ModelMapperService modelMapperService) {
        super();
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
        List<IndividualCustomer> result = this.individualCustomerDao.findAll();
        List<IndividualCustomerSearchListDto> response = result.stream()
                .map(individualCustomer -> modelMapperService.forDto().map(individualCustomer, IndividualCustomerSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<IndividualCustomerSearchListDto>>(response);
    }

    @Override
    public Result Save(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerDao.save(individualCustomer);
        return new SuccesResult("Ekleme İslemi Basarili");
    }

    @Override
    public Result Update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        IndividualCustomer individualCustomer = modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);

        this.individualCustomerDao.save(individualCustomer);
        return new SuccesResult("Guncelleme İslemi Basarili");
    }

    @Override
    public Result Delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        this.individualCustomerDao.deleteById(deleteIndividualCustomerRequest.getIndividualCustomersId());
        return new SuccesResult("Silme İslemi Basarili");
    }


}
