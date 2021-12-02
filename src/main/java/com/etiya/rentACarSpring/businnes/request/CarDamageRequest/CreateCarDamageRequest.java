package com.etiya.rentACarSpring.businnes.request.CarDamageRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
    @JsonIgnore
    private int carDamageId;
    @NotNull
    private int carId;
    @NotNull
    private String damageDescription;
}
