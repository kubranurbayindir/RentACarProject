package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.businnes.abstracts.AdditionalServiceService;
import com.etiya.rentACarSpring.businnes.abstracts.RentalAdditionalServiceService;
import com.etiya.rentACarSpring.businnes.abstracts.RentalService;
import com.etiya.rentACarSpring.businnes.dtos.RentalAdditionalServiceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.CreateRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.DeleteRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.UpdateRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.RentalAdditionalServiceDao;
import com.etiya.rentACarSpring.entities.AdditionalService;
import com.etiya.rentACarSpring.entities.RentalAdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalAdditionalServiceManager implements RentalAdditionalServiceService {
    RentalAdditionalServiceDao rentalAdditionalServiceDao;
    ModelMapperService modelMapperService;
    private RentalService rentalService;
    private AdditionalServiceService additionalServiceService;

    @Autowired
    public RentalAdditionalServiceManager(RentalAdditionalServiceDao rentalAdditionalServiceDao, ModelMapperService modelMapperService,
                                          RentalService rentalService,AdditionalServiceService additionalServiceService) {
        this.rentalAdditionalServiceDao = rentalAdditionalServiceDao;
        this.modelMapperService = modelMapperService;
        this.rentalService=rentalService;
        this.additionalServiceService=additionalServiceService;

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
        Result result = BusinnessRules.run(
                rentalService.checkIfRentalExists(createRentalAdditionalServiceRequest.getRentalId()),
                additionalServiceService.checkIfAdditionalServicexists(createRentalAdditionalServiceRequest.getAdditionalServiceId())
        );
        if (result != null) {
            return result;
        }

        RentalAdditionalService rentalAdditionalService = modelMapperService.forRequest().map(createRentalAdditionalServiceRequest, RentalAdditionalService.class);
        this.rentalAdditionalServiceDao.save(rentalAdditionalService);
        return new SuccesResult("Eklendi");
    }

    @Override
    public Result update(UpdateRentalAdditionalServiceRequest updateRentalAdditionalServiceRequest) {
        Result result = BusinnessRules.run(checkIfRentalAdditionalExists(updateRentalAdditionalServiceRequest.getRentalAdditionalServiceId()),
                rentalService.checkIfRentalExists(updateRentalAdditionalServiceRequest.getRentalId()),
                additionalServiceService.checkIfAdditionalServicexists(updateRentalAdditionalServiceRequest.getAdditionalServiceId())

                );
        if (result != null) {
            return result;
        }
        RentalAdditionalService rentalAdditionalService = modelMapperService.forRequest().map(updateRentalAdditionalServiceRequest, RentalAdditionalService.class);
        this.rentalAdditionalServiceDao.save(rentalAdditionalService);
        return new SuccesResult("Eklendi");
    }

    @Override
    public Result delete(DeleteRentalAdditionalServiceRequest deleteRentalAdditionalServiceRequest) {
        Result result = BusinnessRules.run(checkIfRentalAdditionalExists(deleteRentalAdditionalServiceRequest.getRentalAdditionalServiceId())
        );
        if (result != null) {
            return result;
        }
        this.rentalAdditionalServiceDao.deleteById(deleteRentalAdditionalServiceRequest.getRentalAdditionalServiceId());
        return new SuccesResult("Silindi");
    }


    private Result checkIfRentalAdditionalExists(int rentalAdditionalId) {
        if (!this.rentalAdditionalServiceDao.existsById(rentalAdditionalId)) {
            return new ErrorResult("rentalAdditionalId mevcut değil");
        }
        return new SuccesResult();
    }
}
