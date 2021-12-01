package com.etiya.rentACarSpring.entities.complexTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailForColorAndBrand {
	
	private int carId;
	
	private int brandId;

	private int colorId;
	
	private String brandName;
	
	private String colorName;
	
	private int dailyPrice;

	private String description;
	
	private String imageUrl;
}
