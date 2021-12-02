package com.etiya.rentACarSpring.businnes.abstracts;

import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest.CreateCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest.DeleteCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest.UpdateCorparateRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface CorparateCustomerService {

	Result Add(CreateCorparateRequest createCorparateRequest);
	Result update(UpdateCorparateRequest updateCorparateRequest);
	Result delete(DeleteCorparateRequest deleteCorparateRequest);

	

}
