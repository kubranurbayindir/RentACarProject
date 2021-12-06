package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import com.etiya.rentACarSpring.businnes.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.rentACarSpring.businnes.abstracts.ColorService;
import com.etiya.rentACarSpring.businnes.request.ColorRequest.CreateColorRequest;
import com.etiya.rentACarSpring.businnes.request.ColorRequest.DeleteColorRequest;
import com.etiya.rentACarSpring.businnes.request.ColorRequest.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

@RestController
@RequestMapping("api/colors")
public class ColorsController {
	private ColorService colorService;

	@Autowired
	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}

	@GetMapping("getAll")
	public DataResult<List<ColorSearchListDto>> getAll(){
		return colorService.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) {
		return this.colorService.save(createColorRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
		return this.colorService.update(updateColorRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) {
		return this.colorService.delete(deleteColorRequest);
	}
}
