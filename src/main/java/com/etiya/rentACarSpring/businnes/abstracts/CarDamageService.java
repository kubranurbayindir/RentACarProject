package com.etiya.rentACarSpring.businnes.abstracts;

import com.etiya.rentACarSpring.businnes.dtos.CarDamageSearchListDto;
import com.etiya.rentACarSpring.businnes.request.CarDamageRequest.CreateCarDamageRequest;
import com.etiya.rentACarSpring.businnes.request.CarDamageRequest.DeleteCarDamageRequest;
import com.etiya.rentACarSpring.businnes.request.CarDamageRequest.UpdateCarDamageRequest;

import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

public interface CarDamageService {
    DataResult<List<CarDamageSearchListDto>> getAll();
    Result add(CreateCarDamageRequest createCarDamageRequest);
    Result update(UpdateCarDamageRequest updateCarDamageRequest);
    Result delete(DeleteCarDamageRequest deleteCarDamageRequest);
}
