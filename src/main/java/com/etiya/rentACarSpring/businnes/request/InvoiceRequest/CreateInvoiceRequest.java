package com.etiya.rentACarSpring.businnes.request.InvoiceRequest;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	@JsonIgnore
	private int invoiceId;
	@NotNull
	private String invoiceNumber;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	@NotNull
	private int totalPrice;
	@NotNull
	@Min(1)
	private int totalRentDay;
	@NotNull
	private int userId;
	@NotNull
	private int rentalId;
}
