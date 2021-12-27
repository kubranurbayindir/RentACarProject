package com.etiya.rentACarSpring.businnes.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.businnes.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CreateIndividualCustomerRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface IndividualCustomerService {
	DataResult<List<IndividualCustomerSearchListDto>> getAll();

	Result save(CreateIndividualCustomerRequest createIndividualCustomerRequest);

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
}
