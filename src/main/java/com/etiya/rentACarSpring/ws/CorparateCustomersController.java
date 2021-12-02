package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.businnes.abstracts.CorparateCustomerService;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest.CreateCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest.DeleteCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest.UpdateCorparateRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/corparatecustomers")
public class CorparateCustomersController {
	
	private CorparateCustomerService corparateCustomerService;
	@Autowired
	public CorparateCustomersController(CorparateCustomerService corparateCustomerService) {
		super();
		this.corparateCustomerService = corparateCustomerService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCorparateRequest createCorparateRequest) {
		return this.corparateCustomerService.Add(createCorparateRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCorparateRequest updateCorparateRequest) {
		return this.corparateCustomerService.update(updateCorparateRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCorparateRequest deleteCorparateRequest) {
		return this.corparateCustomerService.delete(deleteCorparateRequest);
	}
	
}
