package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.businnes.abstracts.CarMaintenanceService;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/carmaintenances")
public class CarMaintenancesController {

	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return this.carMaintenanceService.Add(createCarMaintenanceRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.Update(updateCarMaintenanceRequest);
	}
	
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deletecarMaintenanceRequest) {
		return this.carMaintenanceService.Delete(deletecarMaintenanceRequest);
	}
}
