package com.etiya.rentACarSpring.businnes.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalSearchListDto {

	private int rentalId;
	
	private int carId;

	private Date rentDate;

	private Date returnDate;
}
