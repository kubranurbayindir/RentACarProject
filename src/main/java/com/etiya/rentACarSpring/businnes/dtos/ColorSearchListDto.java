package com.etiya.rentACarSpring.businnes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorSearchListDto {
    private int colorId;

    private String colorName;
}
