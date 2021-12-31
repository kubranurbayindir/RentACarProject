package com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


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
	@Pattern(regexp="^[0-9]{10}$",message="Vergi no hatalı veya eksik. Lütfen kontrol ediniz.")
	private String taxNumber;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
}
