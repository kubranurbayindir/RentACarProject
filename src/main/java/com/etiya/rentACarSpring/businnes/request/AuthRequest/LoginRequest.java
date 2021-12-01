package com.etiya.rentACarSpring.businnes.request.AuthRequest;

import javax.validation.constraints.NotNull;

import com.etiya.rentACarSpring.businnes.request.BrandRequest.DeleteBrandRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	
	@NotNull
	private String email;
	@NotNull
	private String password;
}
