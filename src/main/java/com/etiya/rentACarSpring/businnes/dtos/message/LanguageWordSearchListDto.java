package com.etiya.rentACarSpring.businnes.dtos.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageWordSearchListDto {

    private int id;

    private int wordId;

    private int languageId;

    private String translation;
}
