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
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.businnes.abstracts.RentalService;
import com.etiya.rentACarSpring.businnes.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.CreateRentalRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DeleteRentaRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarUpdateRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/rentals")
public class RentalsController {
	private RentalService rentalService;

	@Autowired
	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}

	@GetMapping("all")
	public DataResult<List<RentalSearchListDto>> getAll() {
		return rentalService.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) {

		return this.rentalService.Add(createRentalRequest);
	}
	
	@PutMapping("returnTheCar")
	public Result update(@RequestBody @Valid DropOffCarUpdateRequest dropOffCarUpdateRequest) {
		return this.rentalService.dropOffCarUpdate(dropOffCarUpdateRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteRentaRequest deleteRentalRequest) {
		return this.rentalService.Delete(deleteRentalRequest);
	}

}
