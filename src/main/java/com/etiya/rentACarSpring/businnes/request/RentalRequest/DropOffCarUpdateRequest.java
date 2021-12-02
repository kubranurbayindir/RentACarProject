package com.etiya.rentACarSpring.businnes.request.RentalRequest;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DropOffCarUpdateRequest {

	private int rentalId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;

	private int returnCityId;

	private int returnKilometer;

	private int carId;

	private  int userId;



}
