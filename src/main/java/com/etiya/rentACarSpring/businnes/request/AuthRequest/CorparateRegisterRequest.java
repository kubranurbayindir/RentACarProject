package com.etiya.rentACarSpring.businnes.request.AuthRequest;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	private String password;
	@NotNull
	@NotBlank
	@Pattern(regexp="^[0-9]{10}$",message="Vergi no hatalı veya eksik. Lütfen kontrol ediniz.")
	private String taxNumber;
	@JsonIgnore
	private int findexScore;


	
}
