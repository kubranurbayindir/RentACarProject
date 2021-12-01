package com.etiya.rentACarSpring.businnes.request.ImageRequest;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateImageRequest {
	@JsonIgnore
	private int imageId;

	@NotNull
	private int carId;

	@NotNull
	@NotBlank
	@JsonIgnore
	private MultipartFile file;
}
	