package com.etiya.rentACarSpring.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.businnes.abstracts.CarService;
import com.etiya.rentACarSpring.businnes.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.businnes.request.CarRequest.CreateCarRequest;
import com.etiya.rentACarSpring.businnes.request.CarRequest.DeleteCarRequest;
import com.etiya.rentACarSpring.businnes.request.CarRequest.UpdateCarRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailForColorAndBrand;

@RestController
@RequestMapping("api/cars")
public class CarsController {

	CarService carService;

	@Autowired
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}

	@GetMapping("all")
	public DataResult<List<CarSearchListDto>> getAll() {
		return carService.getAll();
	}

	@GetMapping("detailedCars")
	public DataResult<List<CarDetail>> getDetailedProducts() {
		return carService.getCarWithBrandAndColorDetails();
	}

	@GetMapping("getByDailyPrice")
	public DataResult<List<CarSearchListDto>> getByDailyPrice(@RequestParam("dailyPrice") Integer dailyPrice) {
		return carService.getByDailyPrice(dailyPrice);
	}
	
	@GetMapping("getCarsByColor")
	public DataResult<List<CarDetailForColorAndBrand>> getCarByColor(@RequestParam("colorId") Integer colorId) {
		return carService.getCarByColor(colorId);
	}
	@GetMapping("getCarsByBrand")
	public DataResult<List<CarDetailForColorAndBrand>> getByBrand(@RequestParam("brandId") Integer brandId) {
		return carService.getCarByBrand(brandId);
	}
	@GetMapping("getCarsByCarId")
	public DataResult<List<CarDetailForColorAndBrand>> getByCar(@RequestParam("carId") Integer carId) {
		return carService.getCarByCarId(carId);
	}
	
	@GetMapping("getCarsByCityId")
	public DataResult<List<CarSearchListDto>> getCarsByCity(@RequestParam("cityId") Integer cityId) {
		return carService.getCarByCityId(cityId);
	}

	@GetMapping("getAllWithoutMaintenance")
	public DataResult<List<CarSearchListDto>> getWithoutMaintenance(){	
		return this.carService.getWithoutMaintenanceOfCar();
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {

		return this.carService.save(createCarRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}
}
