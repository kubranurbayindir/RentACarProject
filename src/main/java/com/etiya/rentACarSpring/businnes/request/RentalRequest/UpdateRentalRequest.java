package com.etiya.rentACarSpring.businnes.request.RentalRequest;

import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.City;
import com.etiya.rentACarSpring.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

    private int rentalId;

    private Date rentDate;

    private Date returnDate;

    private  String returnKilometer;

    private int carId;

    private int userId;

    private int takeCityId;

    private int returnCityId;
}
