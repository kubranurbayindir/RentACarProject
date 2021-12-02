package com.etiya.rentACarSpring.core.utilities.adapter.findexScoreServiceAdapter;

import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.core.utilities.adapter.fakeServices.findexService;

@Service
public class findexScroreAdapter implements findexScoreService {

	findexService findexService=new findexService();
	
	@Override
	public Integer getIndividualFindexScore(String idendtityNumber) {

		return	findexService.getIndividualCustomerScore(idendtityNumber);
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
