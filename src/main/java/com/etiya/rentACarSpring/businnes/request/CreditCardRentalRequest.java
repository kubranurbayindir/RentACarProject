package com.etiya.rentACarSpring.businnes.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRentalRequest {
    @NotNull
    @NotBlank
    @Pattern(regexp="(\\d{16})")
    private String cardNumber;
    @Size(min = 3, max = 3)
    @NotNull
    @NotBlank
    private String cvv;
}
