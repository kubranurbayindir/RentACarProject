package com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorparateRequest {
	
	@NotNull
	private int userId;
	@NotNull
	private String companyName;
	@NotNull
	private String taxNumber;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
}
