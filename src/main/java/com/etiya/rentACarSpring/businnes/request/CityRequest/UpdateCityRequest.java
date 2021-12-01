package com.etiya.rentACarSpring.businnes.request.CityRequest;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.City;
import com.etiya.rentACarSpring.entities.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {
	
	@NotNull
	private int cityId;
	@NotNull
	private String cityName;
	

}
