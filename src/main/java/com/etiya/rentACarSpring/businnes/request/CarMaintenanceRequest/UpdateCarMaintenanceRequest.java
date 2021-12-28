package com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
	@NotNull
	private int carMaintenanceId;
	@JsonIgnore
	private Date maintananceDate;
	@NotNull
	private Date returnDate;
	@JsonIgnore
	private int carId;

}
