package com.etiya.rentACarSpring.businnes.request.ColorRequest;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {

	@NotNull
	private String colorName;
}
