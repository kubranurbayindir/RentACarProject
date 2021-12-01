package com.etiya.rentACarSpring.businnes.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.CityService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.request.CityRequest.CreateCityRequest;
import com.etiya.rentACarSpring.businnes.request.CityRequest.DeleteCityRequest;
import com.etiya.rentACarSpring.businnes.request.CityRequest.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CityDao;
import com.etiya.rentACarSpring.entities.City;

@Service
public class CityManager implements CityService {

	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	@Autowired
	public CityManager(CityDao cityDao,ModelMapperService modelMapperService) {
		super();
		this.cityDao = cityDao;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public Result save(CreateCityRequest createCityRequest) {
		City city = modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccesResult(Messages.addedCity);
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		City city  = modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccesResult(Messages.updatedCity);
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		this.cityDao.deleteById(deleteCityRequest.getCityId());
		return new SuccesResult(Messages.deletedCity);
	}

}
