package com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
	@NotNull
	private int carMaintenanseId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date maintananceDate;
	@NotNull
	private Date returnDate;
	@NotNull
	private int carId;
}
