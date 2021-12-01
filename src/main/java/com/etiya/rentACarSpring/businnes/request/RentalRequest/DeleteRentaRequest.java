package com.etiya.rentACarSpring.businnes.request.RentalRequest;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentaRequest {
	@NotNull
	private int rentalId;
}
