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


	private int carId;

	private int dailyPrice;

	private String description;



}
