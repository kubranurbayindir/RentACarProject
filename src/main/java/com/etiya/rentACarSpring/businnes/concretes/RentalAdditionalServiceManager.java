package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.businnes.abstracts.RentalAdditionalServiceService;
import com.etiya.rentACarSpring.businnes.dtos.CarDamageSearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.RentalAdditionalServiceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.CreateRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.DeleteRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.UpdateRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.RentalAdditionalServiceDao;
import com.etiya.rentACarSpring.entities.CarDamage;
import com.etiya.rentACarSpring.entities.RentalAdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalAdditionalServiceManager implements RentalAdditionalServiceService {
    RentalAdditionalServiceDao rentalAdditionalServiceDao;
    ModelMapperService modelMapperService;

    @Autowired
    public RentalAdditionalServiceManager(RentalAdditionalServiceDao rentalAdditionalServiceDao, ModelMapperService modelMapperService) {
        this.rentalAdditionalServiceDao = rentalAdditionalServiceDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<RentalAdditionalServiceSearchListDto>> getAll() {
        List<RentalAdditionalService> result = this.rentalAdditionalServiceDao.findAll();
        List<RentalAdditionalServiceSearchListDto> response = result.stream()
                .map(rentalAdditionalService -> modelMapperService.forDto().map(rentalAdditionalService, RentalAdditionalServiceSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccesDataResult<List<RentalAdditionalServiceSearchListDto>>(response);
    }

    @Override
    public Result add(CreateRentalAdditionalServiceRequest createRentalAdditionalServiceRequest) {
        RentalAdditionalService rentalAdditionalService = modelMapperService.forRequest().map(createRentalAdditionalServiceRequest, RentalAdditionalService.class);
        this.rentalAdditionalServiceDao.save(rentalAdditionalService);
        return new SuccesResult("Eklendi");
    }

    @Override
    public Result update(UpdateRentalAdditionalServiceRequest updateRentalAdditionalServiceRequest) {
        RentalAdditionalService rentalAdditionalService = modelMapperService.forRequest().map(updateRentalAdditionalServiceRequest, RentalAdditionalService.class);
        this.rentalAdditionalServiceDao.save(rentalAdditionalService);
        return new SuccesResult("Eklendi");
    }

    @Override
    public Result delete(DeleteRentalAdditionalServiceRequest deleteRentalAdditionalServiceRequest) {
        this.rentalAdditionalServiceDao.deleteById(deleteRentalAdditionalServiceRequest.getRentalAdditionalServiceId());
        return new SuccesResult("Silindi");
    }
}
