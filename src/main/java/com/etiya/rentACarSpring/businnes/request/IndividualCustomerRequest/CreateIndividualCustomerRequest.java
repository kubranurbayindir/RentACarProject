package com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest;

import java.sql.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateIndividualCustomerRequest {
	@JsonIgnore
	private int individualCustomersId;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	@NotNull
	@Size(min=11,max=11)
	private String identityNumber;
	@NotNull
	private String email;
	@NotNull
	private String password; 
	@NotNull
	@JsonIgnore
	private int findexScore;
//	@NotNull
//	private int userId;

}
