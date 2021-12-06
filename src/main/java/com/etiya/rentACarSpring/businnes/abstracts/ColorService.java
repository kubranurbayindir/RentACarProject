package com.etiya.rentACarSpring.businnes.abstracts;

import com.etiya.rentACarSpring.businnes.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.businnes.request.ColorRequest.CreateColorRequest;
import com.etiya.rentACarSpring.businnes.request.ColorRequest.DeleteColorRequest;
import com.etiya.rentACarSpring.businnes.request.ColorRequest.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

public interface ColorService {
	DataResult<List<ColorSearchListDto>> getAll();
	Result save(CreateColorRequest createColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
	Result delete(DeleteColorRequest deleteColorRequest);
}
