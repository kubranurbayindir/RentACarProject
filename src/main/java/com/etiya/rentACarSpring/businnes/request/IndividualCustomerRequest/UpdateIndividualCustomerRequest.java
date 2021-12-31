package com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
    @NotNull
    private int userId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @NotNull
    @Pattern(regexp="^[1-9]{1}[0-9]{9}[02468]{1}$",message="Tc numaranızı hatalı veya eksik girdiniz lütfen kontrol ediniz.")
    private String identityNumber;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
