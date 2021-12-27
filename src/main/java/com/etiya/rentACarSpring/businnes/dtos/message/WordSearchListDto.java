package com.etiya.rentACarSpring.businnes.dtos.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordSearchListDto {

    private int id;

    private String key;
}
