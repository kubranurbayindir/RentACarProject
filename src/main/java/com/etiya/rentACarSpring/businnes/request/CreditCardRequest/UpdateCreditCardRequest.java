package com.etiya.rentACarSpring.businnes.request.CreditCardRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateCreditCardRequest {
	@NotNull
	private int creditCardId;
	@NotBlank
	@NotNull
	private String cardName;
	@NotBlank
	@Pattern(regexp="(\\d{16})")
	@NotNull
	private String cardNumber;
	@NotBlank
	@NotNull
	@Size(min=3, max=3)
	private String cvv;
	
	@NotNull
	private int userId;

}
