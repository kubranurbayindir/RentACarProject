package com.etiya.rentACarSpring.businnes.request.CreditCardRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {
	@JsonIgnore
	private int creditCardId;

	@NotNull
	private String cardName;

	@NotNull
	private String cardNumber;

	@NotNull
	@Size(min = 3, max = 3)
	private String cvv;

	@NotNull
	private int userId;
	@JsonIgnore
	private boolean isSaved = false;

}
