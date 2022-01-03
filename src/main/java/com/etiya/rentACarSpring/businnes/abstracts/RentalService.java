package com.etiya.rentACarSpring.businnes.abstracts;

import java.util.List;


import com.etiya.rentACarSpring.businnes.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.CreateRentalRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DeleteRentaRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.Rental;


public interface RentalService {
	DataResult<List<RentalSearchListDto>> getAll();
	Result add(CreateRentalRequest createRentalRequest);
	Result delete(DeleteRentaRequest deleteRentalRequest);
	Result checkCarRentalStatus(int carId);
	Rental getById(int rentalId);
	Integer sumAdditionalServicePriceByRentalId(int rentalId);
	Result dropOffCar(DropOffCarRequest dropOffCarRequest);
	DataResult<Integer> getDailyPriceOfRentedCar(int rentalId);
	Result checkIfRentalExists(int rentalId);
	Result checkReturnDate(int rentalId);
	Integer rentOfTotalPrice(DropOffCarRequest dropOffCarRequest);
	Integer rentOfTotalRentDate(DropOffCarRequest dropOffCarRequest);



}
