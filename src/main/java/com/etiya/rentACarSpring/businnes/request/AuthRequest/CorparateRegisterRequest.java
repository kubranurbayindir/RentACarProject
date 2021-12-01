package com.etiya.rentACarSpring.businnes.request.AuthRequest;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorparateRegisterRequest {
	
	
	@NotNull
	private String companyName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String taxNumber;
	@JsonIgnore
	private int findexScore;


	
}
