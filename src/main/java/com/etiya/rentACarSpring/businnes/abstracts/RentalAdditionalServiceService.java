package com.etiya.rentACarSpring.businnes.abstracts;


import com.etiya.rentACarSpring.businnes.dtos.RentalAdditionalServiceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.CreateRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.DeleteRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.UpdateRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;


import java.util.List;

public interface RentalAdditionalServiceService {
    DataResult<List<RentalAdditionalServiceSearchListDto>> getAll();
    Result add(CreateRentalAdditionalServiceRequest createRentalAdditionalServiceRequest);
    Result update(UpdateRentalAdditionalServiceRequest updateRentalAdditionalServiceRequest);
    Result delete(DeleteRentalAdditionalServiceRequest deleteRentalAdditionalServiceRequest);
}
