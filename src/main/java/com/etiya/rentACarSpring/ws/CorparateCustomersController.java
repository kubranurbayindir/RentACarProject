package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import com.etiya.rentACarSpring.businnes.dtos.CorparateCustomerSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACarSpring.businnes.abstracts.CorparateCustomerService;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.CreateCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.DeleteCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.UpdateCorparateRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

@RestController
@RequestMapping("api/corparatecustomers")
public class CorparateCustomersController {
	
	private CorparateCustomerService corparateCustomerService;
	@Autowired
	public CorparateCustomersController(CorparateCustomerService corparateCustomerService) {
		super();
		this.corparateCustomerService = corparateCustomerService;
	}

	@GetMapping("getAll")
	public DataResult<List<CorparateCustomerSearchListDto>> getAll() {
		return corparateCustomerService.getAll();
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
