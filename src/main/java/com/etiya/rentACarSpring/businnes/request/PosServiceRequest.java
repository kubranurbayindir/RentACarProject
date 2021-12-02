package com.etiya.rentACarSpring.businnes.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosServiceRequest {
    @NotBlank
    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotBlank
    @NotNull
    @Size(max = 25)
    private String cardHolderName;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 5)
    private String expirationDate;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 3)
    private String cvv;

    @NotBlank
    @NotNull
    private double price;
}
