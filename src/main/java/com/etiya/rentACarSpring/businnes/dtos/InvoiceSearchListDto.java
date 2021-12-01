package com.etiya.rentACarSpring.businnes.dtos;

import java.sql.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceSearchListDto {
	
	private int invoiceId;

	private String invoiceNumber;

	private Date createDate;

	private Double totalPrice;

	private int totalRentDay;
	
}
