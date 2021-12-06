package com.etiya.rentACarSpring.businnes.request.AuthRequest;

import java.sql.Date;

import javax.validation.constraints.*;

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
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	private String password;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@Size(min=11,max=11)
	@Pattern(regexp="^[1-9]{1}[0-9]{9}[02468]{1}$",message="Tc numaranızı hatalı veya eksik girdiniz lütfen kontrol ediniz.")
	@NotBlank
	private String identityNumber;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
}
