package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import com.etiya.rentACarSpring.businnes.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACarSpring.businnes.abstracts.BrandService;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.CreateBrandRequest;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.DeleteBrandRequest;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.UpdateBrandRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

@RestController
@RequestMapping("api/brands")
public class BrandsController {

	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	@GetMapping("getAll")
	public DataResult<List<BrandSearchListDto>> getAll(){
		return brandService.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
		return this.brandService.save(createBrandRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}
}
