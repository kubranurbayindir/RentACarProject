package com.etiya.rentACarSpring.businnes.request.CarDamageRequest;

import com.etiya.rentACarSpring.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {
    @NotNull
    private int carDamageId;
    @NotNull
    private int carId;
    @NotNull
    private String damageDescription;
}
