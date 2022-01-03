package com.etiya.rentACarSpring.businnes.request.CarRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateCarRequest {
	@NotNull
	private int carId;
	@NotNull
	private int brandId;
	@NotNull
	private int colorId;
	@NotNull
	@Min(100)
	private int dailyPrice;
	@Size(min=10,max=200)
	@NotNull
	private String description;
	@NotNull
	private int kilometer;
	@NotNull
	private int cityId;
	@NotNull
	private int modelYear;
}
