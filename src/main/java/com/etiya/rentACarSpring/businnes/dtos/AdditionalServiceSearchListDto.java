package com.etiya.rentACarSpring.businnes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceSearchListDto {
    private int additionalServiceId;

    private String additionalServiceName;

    private int additionalServicePrice;
}
