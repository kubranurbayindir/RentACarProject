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
public class CreateInvoiceDateRequest {
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date minDate;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date maxDate;
}
