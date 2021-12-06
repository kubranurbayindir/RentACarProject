package com.etiya.rentACarSpring.businnes.request.InvoiceRequest;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	@NotNull
	private int invoiceId;

	private String invoiceNumber;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	private int totalPrice;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private int totalRentDay;

	private int rentalId;
}
