package com.etiya.rentACarSpring.businnes.request.AdditionalServiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
    @NotNull
    private int additionalServiceId;

    private String additionalServiceName;

    private int additionalServicePrice;

    //private int rentalId;
}
