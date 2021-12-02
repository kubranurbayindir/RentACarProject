package com.etiya.rentACarSpring.core.utilities.adapter.findexScoreServiceAdapter;

import com.etiya.rentACarSpring.businnes.request.AuthRequest.CorparateRegisterRequest;
import com.etiya.rentACarSpring.entities.CorparateCustomer;

public interface findexScoreService {

	Integer getIndividualFindexScore(String identityNumber);
	Integer getCorparateFindexScore(String taxNumber);
	Integer sendCarFindexScore();
	
}
