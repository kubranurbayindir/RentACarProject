package com.etiya.rentACarSpring.businnes.abstracts;

import java.util.List;


import com.etiya.rentACarSpring.businnes.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.CreateRentalRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DeleteRentaRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarUpdateRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.Rental;


public interface RentalService {
	DataResult<List<RentalSearchListDto>> getAll();
	Result Add(CreateRentalRequest createRentalRequest);
	Result dropOffCarUpdate(DropOffCarUpdateRequest dropOffCarUpdateRequest);
	Result Delete(DeleteRentaRequest deleteRentalRequest);
	DataResult<List<Rental>> getByCar(int carId);
	Result checkCarRentalStatus(int carId);
	Rental getById(int rentalId);
}
