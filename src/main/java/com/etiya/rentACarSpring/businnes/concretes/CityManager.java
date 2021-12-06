package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.businnes.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.entities.Color;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.CityService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.request.CityRequest.CreateCityRequest;
import com.etiya.rentACarSpring.businnes.request.CityRequest.DeleteCityRequest;
import com.etiya.rentACarSpring.businnes.request.CityRequest.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CityDao;
import com.etiya.rentACarSpring.entities.City;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        super();
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CitySearchListDto>> getAll() {
        List<City> result = this.cityDao.findAll();
        List<CitySearchListDto> response = result.stream()
                .map(city -> modelMapperService.forDto().map(city, CitySearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<CitySearchListDto>>(response);
    }

    @Override
    public Result save(CreateCityRequest createCityRequest) {
        Result result = BusinnessRules.run(checkCityNameDublicated(createCityRequest.getCityName()));
        if (result != null) {
            return result;
        }

        City city = modelMapperService.forRequest().map(createCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccesResult(Messages.addedCity);
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) {
        City city = modelMapperService.forRequest().map(updateCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccesResult(Messages.updatedCity);
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) {
        this.cityDao.deleteById(deleteCityRequest.getCityId());
        return new SuccesResult(Messages.deletedCity);
    }

    @Override
    public DataResult<City> getbyId(int cityId) {
        return new SuccesDataResult<City>(this.cityDao.getById(cityId));
    }

    private Result checkCityNameDublicated(String cityName) {
        City city = this.cityDao.getByCityName(cityName);
        if (city != null) {
            return new ErrorResult("Şehir kayıtlı.");
        }
        return new SuccesResult();
    }
}
