package com.etiya.rentACarSpring.businnes.request.AdditionalServiceRequest;

import com.etiya.rentACarSpring.entities.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {
    @JsonIgnore
    private int additionalServiceId;
    @NotNull
    @NotBlank
    private String additionalServiceName;

    private int additionalServicePrice;

   // private int rentalId;
}
