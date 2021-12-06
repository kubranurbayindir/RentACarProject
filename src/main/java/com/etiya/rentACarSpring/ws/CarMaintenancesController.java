package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import com.etiya.rentACarSpring.businnes.dtos.CarMaintenanceSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACarSpring.businnes.abstracts.CarMaintenanceService;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

@RestController
@RequestMapping("api/carmaintenances")
public class CarMaintenancesController {

	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}

	@GetMapping("getAll")
	public DataResult<List<CarMaintenanceSearchListDto>> getAll() {
		return carMaintenanceService.getAll();
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
