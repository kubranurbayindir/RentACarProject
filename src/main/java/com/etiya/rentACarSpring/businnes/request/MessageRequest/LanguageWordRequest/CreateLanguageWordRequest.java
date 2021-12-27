package com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLanguageWordRequest {

    @NotNull
    private int wordId;
    @NotNull
    private int languageId;
    @NotNull
    private String translation;
}
