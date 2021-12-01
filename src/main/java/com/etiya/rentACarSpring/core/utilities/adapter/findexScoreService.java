package com.etiya.rentACarSpring.core.utilities.adapter;

import com.etiya.rentACarSpring.businnes.request.AuthRequest.CorparateRegisterRequest;
import com.etiya.rentACarSpring.entities.CorparateCustomer;

public interface findexScoreService {

	Integer sendUserFindexScore();
	Integer getCorparateFindexScore(String taxNumber);
	Integer sendCarFindexScore();
	
}
