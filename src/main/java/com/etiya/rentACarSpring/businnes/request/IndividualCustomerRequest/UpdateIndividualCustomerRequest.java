package com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
    @NotNull
    private int userId;

    @NotNull
    @Size(min=2,max=16)
    private String firstName;

    @NotNull
    @Size(min=2,max=16)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;
}
