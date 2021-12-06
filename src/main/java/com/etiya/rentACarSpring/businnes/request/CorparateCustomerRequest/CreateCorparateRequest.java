package com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorparateRequest {
	
	@JsonIgnore
	private int corparateCustomerId;
	@NotNull
	private String companyName;
	@NotNull
	private String taxNumber;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	@JsonIgnore
	private int findexScore;


}
