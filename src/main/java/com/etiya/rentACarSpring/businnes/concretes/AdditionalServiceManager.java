package com.etiya.rentACarSpring.businnes.concretes;


import com.etiya.rentACarSpring.businnes.abstracts.AdditionalServiceService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.dtos.AdditionalServiceSearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.CarDamageSearchListDto;
import com.etiya.rentACarSpring.businnes.request.AdditionalServiceRequest.CreateAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.AdditionalServiceRequest.DeleteAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.AdditionalServiceRequest.UpdateAdditionalServiceRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.rentACarSpring.entities.AdditionalService;
import com.etiya.rentACarSpring.entities.Brand;
import com.etiya.rentACarSpring.entities.CarDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

    private AdditionalServiceDao additionalServiceDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
        this.additionalServiceDao = additionalServiceDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result save(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        Result result=BusinnessRules.run(checkAdditionalServiceNameDublicated(createAdditionalServiceRequest.getAdditionalServiceName()));
        if (result != null) {
            return result;
        }

        AdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccesResult("Eklendi");
    }

    @Override
    public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        Result result=BusinnessRules.run(checkAdditionalServiceNameDublicated(updateAdditionalServiceRequest.getAdditionalServiceName()));
        if (result != null) {
            return result;
        }

        AdditionalService additionalService = modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccesResult("Eklendi");
    }

    @Override
    public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
        this.additionalServiceDao.deleteById(deleteAdditionalServiceRequest.getAdditionalServiceId());
        return new SuccesResult("Silindi");
    }

    @Override
    public DataResult<List<AdditionalServiceSearchListDto>> getAll() {
        List<AdditionalService> result = this.additionalServiceDao.findAll();
        List<AdditionalServiceSearchListDto> response = result.stream()
                .map(additionalService -> modelMapperService.forDto().map(additionalService, AdditionalServiceSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccesDataResult<List<AdditionalServiceSearchListDto>>(response);
    }

    private Result checkAdditionalServiceNameDublicated(String additionalServiceName) {
        AdditionalService additionalService=this.additionalServiceDao.getByAdditionalServiceName(additionalServiceName.toLowerCase());
        if (additionalService!=null) {
            return new ErrorResult("Bu isime ait baska bir hizmet bulunmaktadır.");
        }
        return new SuccesResult();
    }


}
