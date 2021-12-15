package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.businnes.abstracts.CarDamageService;
import com.etiya.rentACarSpring.businnes.abstracts.CarService;
import com.etiya.rentACarSpring.businnes.dtos.CarDamageSearchListDto;
import com.etiya.rentACarSpring.businnes.request.CarDamageRequest.CreateCarDamageRequest;
import com.etiya.rentACarSpring.businnes.request.CarDamageRequest.DeleteCarDamageRequest;
import com.etiya.rentACarSpring.businnes.request.CarDamageRequest.UpdateCarDamageRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarDamageDao;

import com.etiya.rentACarSpring.entities.CarDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {

    private CarDamageDao carDamageDao;
    private ModelMapperService modelMapperService;
    private CarService carService;

    @Autowired
    public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService, CarService carService) {
        this.carDamageDao = carDamageDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;

    }

    @Override
    public DataResult<List<CarDamageSearchListDto>> getAll() {
        List<CarDamage> result = this.carDamageDao.findAll();
        List<CarDamageSearchListDto> response = result.stream()
                .map(car -> modelMapperService.forDto().map(car, CarDamageSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccesDataResult<List<CarDamageSearchListDto>>(response);
    }

    @Override
    public Result add(CreateCarDamageRequest createCarDamageRequest) {
        Result result = BusinnessRules.run(checkCarExistsInGallery(createCarDamageRequest.getCarId()));
        if (result != null) {
            return result;
        }

        CarDamage carDamage = modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);
        return new SuccesResult("Eklendi");
    }

    @Override
    public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
        Result result = BusinnessRules.run(checkCarExistsInGallery(updateCarDamageRequest.getCarId()),
                checkIfCarDamageIdExists(updateCarDamageRequest.getCarDamageId()));
        if (result != null) {
            return result;
        }

        CarDamage carDamage = modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);
        return new SuccesResult("Güncellendi");
    }

    @Override
    public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) throws EntityNotFoundException {
        Result result = BusinnessRules.run(
                checkIfCarDamageIdExists(deleteCarDamageRequest.getCarDamageId()));
        if (result != null) {
            return result;
        }

        this.carDamageDao.deleteById(deleteCarDamageRequest.getCarDamageId());
        return new SuccesResult("Silindi");
    }

    @Override
    public Result checkIfCarDamageIdExists(int carDamageId) {
        if (!this.carDamageDao.existsById(carDamageId)) {
            return new ErrorResult("carDamageId Id bulunamadı");
        }
        return new SuccesResult();
    }

    private Result checkCarExistsInGallery(int id) {
        boolean isExisting = carService.checkCarExistsInGallery(id).isSuccess();
        if (isExisting) {
            return new SuccesResult();
        }
        return new ErrorResult("Galeride böyle bir araba yok.");
    }
}
