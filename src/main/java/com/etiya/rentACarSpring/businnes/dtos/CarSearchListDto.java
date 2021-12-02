package com.etiya.rentACarSpring.businnes.dtos;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CarSearchListDto {
	@JsonProperty(value = "id")
	private int carId;
	 @JsonProperty(value = "daily_price")
	private int dailyPrice;
	 @JsonProperty(value = "description")
	private String description;

	//private int findexScore;

}
