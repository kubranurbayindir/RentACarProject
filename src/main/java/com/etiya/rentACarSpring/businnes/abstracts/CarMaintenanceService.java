package com.etiya.rentACarSpring.businnes.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.businnes.dtos.CarMaintenanceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.CarMaintenance;

public interface CarMaintenanceService {
	DataResult<List<CarMaintenanceSearchListDto>> getAll();

	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);

	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);

	Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);

	DataResult<CarMaintenance> getById(int carId);

	Result checkIfCarIsAtMaintenance(int carId);
}
