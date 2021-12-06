package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.businnes.dtos.CorparateCustomerSearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.entities.Rental;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CorparateCustomerManager(CorparateCustomerDao corparateCustomerDao, ModelMapperService modelMapperService) {
        super();
        this.corparateCustomerDao = corparateCustomerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CorparateCustomerSearchListDto>> getAll() {
        List<CorparateCustomer> result = this.corparateCustomerDao.findAll();
        List<CorparateCustomerSearchListDto> response = result.stream()
                .map(corparateCustomer -> modelMapperService.forDto().map(corparateCustomer, CorparateCustomerSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<CorparateCustomerSearchListDto>>(response);
    }

    @Override
    public Result Add(CreateCorparateRequest createCorparateRequest) {
        Result result = BusinnessRules.run(checkIfTaxNumberExists(createCorparateRequest.getTaxNumber()));
        if (result != null) {
            return result;
        }

        CorparateCustomer corparateCustomer = modelMapperService.forRequest().map(createCorparateRequest, CorparateCustomer.class);
        this.corparateCustomerDao.save(corparateCustomer);
        return new SuccesResult("Ekleme İslemi Basarili");
    }

    @Override
    public Result update(UpdateCorparateRequest updateCorparateRequest) {
        Result result = BusinnessRules.run(checkIfTaxNumberExists(updateCorparateRequest.getTaxNumber()));
        if (result != null) {
            return result;
        }

        CorparateCustomer corparateCustomer = modelMapperService.forRequest().map(updateCorparateRequest, CorparateCustomer.class);
        this.corparateCustomerDao.save(corparateCustomer);
        return new SuccesResult("Güncelleme İşlemi Başarılı");
    }

    @Override
    public Result delete(DeleteCorparateRequest deleteCorparateRequest) {
        this.corparateCustomerDao.deleteById(deleteCorparateRequest.getCorparateCustomerId());
        return new SuccesResult("Silme İşlemi Başarılı");
    }

    private Result checkIfTaxNumberExists(String taxNumber) {

        if (this.corparateCustomerDao.existsByTaxNumber(taxNumber))
            return new ErrorResult("Vergi numarası sistemde kayıtlıdır.");

        return new SuccesResult();
    }

}
