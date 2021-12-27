package com.etiya.rentACarSpring.ws;

import java.io.IOException;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.rentACarSpring.businnes.abstracts.ImageService;
import com.etiya.rentACarSpring.businnes.dtos.ImageSearchListDto;
import com.etiya.rentACarSpring.businnes.request.ImageRequest.CreateImageRequest;
import com.etiya.rentACarSpring.businnes.request.ImageRequest.DeleteImageRequest;
import com.etiya.rentACarSpring.businnes.request.ImageRequest.UpdateImageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/images")
public class ImagesController {
	private ImageService imageService;
	@Autowired
	public ImagesController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}

	@GetMapping("getAll")
	public DataResult<List<ImageSearchListDto>> getAll() {
		return imageService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestParam("carId") int carId, MultipartFile file) throws IOException {

		CreateImageRequest createImageRequest = new CreateImageRequest();
		createImageRequest.setCarId(carId);
		createImageRequest.setFile(file);

		return this.imageService.add(createImageRequest);
	}
	
	@GetMapping("/getCarImageDetailByCarId")
	public DataResult<List<ImageSearchListDto>> getCarImageDetailByCarId(int carId) {
		return this.imageService.getCarImageDetailByCarId(carId);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@Valid DeleteImageRequest deleteImageRequest) {
		return this.imageService.delete(deleteImageRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestParam("carImageId") int carImageId, @RequestParam("file") MultipartFile file)
			throws IOException {

		UpdateImageRequest updateImageRequest = new UpdateImageRequest();
		updateImageRequest.setImageId(carImageId);
		updateImageRequest.setFile(file);

		return this.imageService.update(updateImageRequest);
	}
}
