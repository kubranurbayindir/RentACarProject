package com.etiya.rentACarSpring.businnes.request.AuthRequest;

import java.sql.Date;

import javax.validation.constraints.Email;
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
public class IndividualRegisterRequest {
	
	@JsonIgnore
	private int individualCustomersId;
	@JsonIgnore
	private int userId;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@Size(min=11,max=11)
	private String identityNumber;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
}
