package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.businnes.abstracts.CarDamageService;
import com.etiya.rentACarSpring.businnes.dtos.CarDamageSearchListDto;
import com.etiya.rentACarSpring.businnes.request.CarDamageRequest.CreateCarDamageRequest;
import com.etiya.rentACarSpring.businnes.request.CarDamageRequest.DeleteCarDamageRequest;
import com.etiya.rentACarSpring.businnes.request.CarDamageRequest.UpdateCarDamageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/carDamages")
public class CarDamagesController {
    private CarDamageService carDamageService;

    @Autowired
    public CarDamagesController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }

    @GetMapping("all")
    public DataResult<List<CarDamageSearchListDto>> getAll() {
        return  this.carDamageService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest) {
        return this.carDamageService.add(createCarDamageRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateCarDamageRequest updateCarDamageRequest) {
        return this.carDamageService.update(updateCarDamageRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteCarDamageRequest deleteCarDamageRequest) {
        return this.carDamageService.delete(deleteCarDamageRequest);
    }
}
