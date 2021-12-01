package com.etiya.rentACarSpring.core.utilities.adapter;

import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.fakeServices.findexService;

@Service
public class findexScroreAdapter implements findexScoreService {

	findexService findexService=new findexService();
	
	@Override
	public Integer sendUserFindexScore() {

		return	findexService.getIndividualCustomerScore();
	}

	@Override
	public Integer getCorparateFindexScore(String taxNumber) {
		return findexService.getCorparateCustomerScore(taxNumber) ;
	}

	@Override
	public Integer sendCarFindexScore() {

		return	findexService.getCarScore();
	}


	

	

}
