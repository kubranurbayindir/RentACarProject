package com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest;


import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorparateRequest {
	
	@NotNull
	private int corparateCustomerId;
	@NotNull
	private String companyName;
	@NotNull
	private String taxNumber;
	@NotNull
	private int rentalId;
}
