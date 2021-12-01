package com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest;

import java.sql.Date;

import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.CarMaintenance;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	@JsonIgnore
	private int carMaintenanceId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date maintananceDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;

	private int carId;
}
