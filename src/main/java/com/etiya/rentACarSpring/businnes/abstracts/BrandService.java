package com.etiya.rentACarSpring.businnes.abstracts;

import com.etiya.rentACarSpring.businnes.request.BrandRequest.CreateBrandRequest;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.DeleteBrandRequest;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.UpdateBrandRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface BrandService {
	
	Result save(CreateBrandRequest createBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);
}
