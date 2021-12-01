package com.etiya.rentACarSpring.businnes.request.ColorRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	private int colorId;

	private String colorName;
}
