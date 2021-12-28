package com.etiya.rentACarSpring.businnes.request.RentalRequest;

import com.etiya.rentACarSpring.entities.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

    @NotNull
    private int rentalId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rentDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    @NotNull
    private String returnKilometer;
    @NotNull
    private int car;
    @NotNull
    private int user;
    @NotNull
    private int takeCity;
    @NotNull
    private int returnCity;


}
