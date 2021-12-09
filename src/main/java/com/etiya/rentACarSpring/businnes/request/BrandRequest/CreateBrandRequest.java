package com.etiya.rentACarSpring.businnes.request.BrandRequest;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {

	@JsonIgnore
	private int brandId;

	@NotNull
	@NotBlank(message = "Marka Boş Bırakılamaz.")
	private String brandName;

}
