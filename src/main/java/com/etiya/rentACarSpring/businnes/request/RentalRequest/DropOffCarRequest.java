package com.etiya.rentACarSpring.businnes.request.RentalRequest;

import java.sql.Date;

import com.etiya.rentACarSpring.businnes.request.CreditCardRentalRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DropOffCarRequest {

	private int rentalId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;

	private int returnCityId;

	private int returnKilometer;

	//private int carId;

	private CreditCardRentalRequest creditCardRentalRequest;


}
