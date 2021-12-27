package com.etiya.rentACarSpring.businnes.abstracts;

import com.etiya.rentACarSpring.businnes.dtos.CorparateCustomerSearchListDto;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.CreateCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.DeleteCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.UpdateCorparateRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

public interface CorparateCustomerService {
	DataResult<List<CorparateCustomerSearchListDto>> getAll();
	Result add(CreateCorparateRequest createCorparateRequest);
	Result update(UpdateCorparateRequest updateCorparateRequest);
	Result delete(DeleteCorparateRequest deleteCorparateRequest);

	

}
