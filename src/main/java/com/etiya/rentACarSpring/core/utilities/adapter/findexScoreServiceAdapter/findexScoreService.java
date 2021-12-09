package com.etiya.rentACarSpring.core.utilities.adapter.findexScoreServiceAdapter;

public interface findexScoreService {

	Integer getIndividualFindexScore(String identityNumber);
	Integer getCorparateFindexScore(String taxNumber);
	Integer sendCarFindexScore();
	
}
