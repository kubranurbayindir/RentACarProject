package com.etiya.rentACarSpring.businnes.request.RentalRequest;

import java.sql.Date;

import com.etiya.rentACarSpring.businnes.request.CreditCardRentalRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DropOffCarRequest {

	@NotNull
	private int rentalId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;

	@NotNull
	private int returnCityId;

	@NotNull
	private int returnKilometer;

	@NotNull
	private CreditCardRentalRequest creditCardRentalRequest;

}
