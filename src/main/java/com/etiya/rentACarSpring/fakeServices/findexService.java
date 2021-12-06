package com.etiya.rentACarSpring.fakeServices;

import java.util.Random;

public class findexService {

	public Integer getIndividualCustomerScore(String identityNumber) {
		return 1000;
	}
	
	public Integer getCorparateCustomerScore(String taxNumber) {
		return 1000;
	}

	public Integer getCarScore() {
		Random rand = new Random();
		return rand.nextInt(1900);
	}

}
