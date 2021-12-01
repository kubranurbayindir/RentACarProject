package com.etiya.rentACarSpring.businnes.request.CityRequest;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {
	@NotNull
	@JsonIgnore
	private int cityId;
	@NotNull
	private String cityName;
}
