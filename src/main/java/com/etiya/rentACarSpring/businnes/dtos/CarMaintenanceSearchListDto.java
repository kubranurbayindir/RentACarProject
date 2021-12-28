package com.etiya.rentACarSpring.businnes.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceSearchListDto {

	private int carMaintenanceId;

	private Date maintananceDate;

	private Date returnDate;

	private int carCarId;
}
