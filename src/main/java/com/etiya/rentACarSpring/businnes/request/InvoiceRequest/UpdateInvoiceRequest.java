package com.etiya.rentACarSpring.businnes.request.InvoiceRequest;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	
	private int invoiceId;
	
	private String invoiceNumber;

	private Date createDate;

	private Double totalPrice;

	private int totalRentDay;
	
	private int userId;
	
	private int rentalId;
}
