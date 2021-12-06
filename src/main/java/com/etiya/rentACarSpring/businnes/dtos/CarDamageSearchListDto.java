package com.etiya.rentACarSpring.businnes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageSearchListDto {

    private int carDamageId;

    private int carCarId;

    private String damageDescription;
}
