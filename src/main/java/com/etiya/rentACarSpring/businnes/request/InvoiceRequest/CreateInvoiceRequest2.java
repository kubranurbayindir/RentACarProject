package com.etiya.rentACarSpring.businnes.request.InvoiceRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest2 {


    private int rentalId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    private int returnCityId;

    private int returnKilometer;

    private int carId;
}

