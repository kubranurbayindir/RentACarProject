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

import com.etiya.rentACarSpring.businnes.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.businnes.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/individualcustomers")
public class IndividualCustomersController {
	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}

	@GetMapping("getAll")
	public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
		return individualCustomerService.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {

		return this.individualCustomerService.Save(createIndividualCustomerRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateRentalRequest) {
		return this.individualCustomerService.Update(updateRentalRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.Delete(deleteIndividualCustomerRequest);
	}

}
