package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import com.etiya.rentACarSpring.businnes.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACarSpring.businnes.abstracts.CityService;
import com.etiya.rentACarSpring.businnes.request.CityRequest.CreateCityRequest;
import com.etiya.rentACarSpring.businnes.request.CityRequest.DeleteCityRequest;
import com.etiya.rentACarSpring.businnes.request.CityRequest.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

@RestController
@RequestMapping("api/cities")
public class CitiesController {
	private CityService cityService;

	@Autowired
	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}

	@GetMapping("getAll")
	public DataResult<List<CitySearchListDto>> getAll(){
		return cityService.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {
		return this.cityService.save(createCityRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) {
		return this.cityService.update(updateCityRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest) {
		return this.cityService.delete(deleteCityRequest);
	}

}
