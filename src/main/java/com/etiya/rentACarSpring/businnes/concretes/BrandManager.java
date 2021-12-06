package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.businnes.abstracts.MessageService;
import com.etiya.rentACarSpring.businnes.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.entities.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.BrandService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.CreateBrandRequest;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.DeleteBrandRequest;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.UpdateBrandRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.BrandDao;
import com.etiya.rentACarSpring.entities.Brand;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {

    private BrandDao brandDao;
    private ModelMapperService modelMapperService;
    private MessageService messageService;

    @Autowired
    public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService, MessageService messageService) {
        super();
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
        this.messageService = messageService;
    }

    @Override
    public DataResult<List<BrandSearchListDto>> getAll() {
        List<Brand> result = this.brandDao.findAll();
        List<BrandSearchListDto> response = result.stream()
                .map(brand -> modelMapperService.forDto().map(brand, BrandSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<BrandSearchListDto>>(response);
    }

    @Override
    public Result save(CreateBrandRequest createBrandRequest) {
        Result result = BusinnessRules.run(checkBrandNameDublicated(createBrandRequest.getBrandName()));
        if (result != null) {
            return result;
        }

        Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        this.brandDao.save(brand);

        return new SuccesResult(messageService.getByEnglishMessageByMessageId(1));
    }

    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {
        Result result = BusinnessRules.run(checkBrandNameDublicated(updateBrandRequest.getBrandName()));
        if (result != null) {
            return result;
        }

        Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        this.brandDao.save(brand);
        return new SuccesResult(messageService.getByEnglishMessageByMessageId(2));
    }

    @Override
    public Result delete(DeleteBrandRequest deleteBrandRequest) {

        this.brandDao.deleteById(deleteBrandRequest.getBrandId());
        return new SuccesResult(messageService.getByEnglishMessageByMessageId(3));
    }

    private Result checkBrandNameDublicated(String brandName) {
        Brand brand = this.brandDao.getByBrandName(brandName);
        if (brand != null) {
            return new ErrorResult(messageService.getByEnglishMessageByMessageId(4));
        }

        return new SuccesResult();
    }

}
