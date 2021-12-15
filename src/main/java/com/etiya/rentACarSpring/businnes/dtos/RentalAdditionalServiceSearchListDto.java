package com.etiya.rentACarSpring.businnes.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAdditionalServiceSearchListDto {

    private int rentalAdditionalServiceId;

    private int additionalServiceId;

    private int rentalId;
}
