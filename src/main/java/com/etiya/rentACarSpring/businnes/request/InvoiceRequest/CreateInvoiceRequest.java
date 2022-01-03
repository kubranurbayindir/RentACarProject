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
	@JsonIgnore
	private String invoiceNumber;
	@JsonIgnore
	private Date createDate;
	@JsonIgnore
	private int totalPrice;
	@JsonIgnore
	private int totalRentDay;
	@JsonIgnore
	private int returnCityId;
	@JsonIgnore
	private Date rentDate;
	@JsonIgnore
	private Date returnDate;
	@NotNull
	private int rentalId;
}


