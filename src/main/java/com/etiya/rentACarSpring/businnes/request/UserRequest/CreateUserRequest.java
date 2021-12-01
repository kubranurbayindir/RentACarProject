package com.etiya.rentACarSpring.businnes.request.UserRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
	
	@JsonIgnore
	private int userId;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min=6,max=16)
	private String password;
}
