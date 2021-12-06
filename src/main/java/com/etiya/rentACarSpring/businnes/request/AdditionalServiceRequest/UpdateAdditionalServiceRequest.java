package com.etiya.rentACarSpring.businnes.request.AdditionalServiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
    @NotNull
    private int additionalServiceId;
    @NotNull
    @NotBlank
    private String additionalServiceName;

    private int additionalServicePrice;

    //private int rentalId;
}
