package com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalAdditionalServiceRequest {
    @NotNull
    private int rentalAdditionalServiceId;
    @NotNull
    private int additionalServiceId;
    @NotNull
    private int rentalId;
}
