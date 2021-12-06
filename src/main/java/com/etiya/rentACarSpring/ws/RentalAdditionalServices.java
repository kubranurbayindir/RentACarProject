package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.businnes.abstracts.RentalAdditionalServiceService;
import com.etiya.rentACarSpring.businnes.dtos.RentalAdditionalServiceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.CreateRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.DeleteRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest.UpdateRentalAdditionalServiceRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rentalAdditionalServices")
public class RentalAdditionalServices {
    private RentalAdditionalServiceService rentalAdditionalServiceService;
    @Autowired
    public RentalAdditionalServices(RentalAdditionalServiceService rentalAdditionalServiceService) {
        this.rentalAdditionalServiceService = rentalAdditionalServiceService;
    }

    @GetMapping("getAll")
    public DataResult<List<RentalAdditionalServiceSearchListDto>> getAll() {
        return  this.rentalAdditionalServiceService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateRentalAdditionalServiceRequest createRentalAdditionalServiceRequest) {
        return this.rentalAdditionalServiceService.add(createRentalAdditionalServiceRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateRentalAdditionalServiceRequest updateRentalAdditionalServiceRequest) {
        return this.rentalAdditionalServiceService.update(updateRentalAdditionalServiceRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteRentalAdditionalServiceRequest deleteRentalAdditionalServiceRequest) {
        return this.rentalAdditionalServiceService.delete(deleteRentalAdditionalServiceRequest);
    }
}
