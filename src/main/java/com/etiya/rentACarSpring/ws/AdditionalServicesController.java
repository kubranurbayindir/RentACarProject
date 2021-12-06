package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.businnes.abstracts.AdditionalServiceService;
import com.etiya.rentACarSpring.businnes.dtos.AdditionalServiceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.AdditionalServiceRequest.CreateAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.AdditionalServiceRequest.DeleteAdditionalServiceRequest;
import com.etiya.rentACarSpring.businnes.request.AdditionalServiceRequest.UpdateAdditionalServiceRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/additionalService")
public class AdditionalServicesController {

    private AdditionalServiceService additionalServiceService;
    @Autowired
    public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @GetMapping("all")
    public DataResult<List<AdditionalServiceSearchListDto>> getAll() {
        return additionalServiceService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        return this.additionalServiceService.save(createAdditionalServiceRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        return this.additionalServiceService.update(updateAdditionalServiceRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
        return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
    }
}
