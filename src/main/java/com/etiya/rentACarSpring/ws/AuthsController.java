package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.businnes.abstracts.AuthService;
import com.etiya.rentACarSpring.businnes.request.AuthRequest.CorparateRegisterRequest;
import com.etiya.rentACarSpring.businnes.request.AuthRequest.IndividualRegisterRequest;
import com.etiya.rentACarSpring.businnes.request.AuthRequest.LoginRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/auth")
public class AuthsController {
	
	private AuthService authService;
	
	@Autowired
	public AuthsController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("registerIndividualCustomer")
	public Result add(@RequestBody @Valid IndividualRegisterRequest individualRegisterRequest) {
		return this.authService.individualRegister(individualRegisterRequest);
	}
	
	
	@PostMapping("registerCorparateCustomer")
	public Result add(@RequestBody @Valid CorparateRegisterRequest corparateRegisterRequest) {
		return this.authService.corparateRegister(corparateRegisterRequest);
	
	}
	
	@PostMapping("/login")
	Result login(@Valid @RequestBody LoginRequest loginRequest) {
		return this.authService.login(loginRequest);
	}
}
