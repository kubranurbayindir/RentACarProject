package com.etiya.rentACarSpring.businnes.request.RentalAdditionalServiceRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalAdditionalServiceRequest {
   @JsonIgnore
    private int rentalAdditionalServiceId;
    @NotNull
    private int additionalServiceId;
    @NotNull
    private int rentalId;
}
