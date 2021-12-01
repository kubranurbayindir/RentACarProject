package com.etiya.rentACarSpring.entities.complexTypes;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDetail {
	
	private int rentalId;

	private Date returnDate;
	
	private Date carId;
	
	
}
