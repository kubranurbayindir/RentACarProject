package com.etiya.rentACarSpring.businnes.abstracts;

import com.etiya.rentACarSpring.businnes.request.AuthRequest.CorparateRegisterRequest;
import com.etiya.rentACarSpring.businnes.request.AuthRequest.IndividualRegisterRequest;
import com.etiya.rentACarSpring.businnes.request.AuthRequest.LoginRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface AuthService {
	
	Result individualRegister(IndividualRegisterRequest individualRegisterRequest);
	Result corparateRegister(CorparateRegisterRequest corparateRegisterRequest);	
	Result login(LoginRequest loginRequest);
	Result checkCustomerEmailIsTrue(LoginRequest loginRequest);
	Result checkEmailIfExists(String email);
}
