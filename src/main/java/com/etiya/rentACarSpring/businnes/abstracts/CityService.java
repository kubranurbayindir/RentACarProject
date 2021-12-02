package com.etiya.rentACarSpring.businnes.abstracts;

import com.etiya.rentACarSpring.businnes.request.CityRequest.CreateCityRequest;
import com.etiya.rentACarSpring.businnes.request.CityRequest.DeleteCityRequest;
import com.etiya.rentACarSpring.businnes.request.CityRequest.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.City;

public interface CityService {

	Result save(CreateCityRequest createCityRequest);
	Result update(UpdateCityRequest updateCityRequest);
	Result delete(DeleteCityRequest deleteCityRequest);
	DataResult<City> getbyId(int cityId);
}
