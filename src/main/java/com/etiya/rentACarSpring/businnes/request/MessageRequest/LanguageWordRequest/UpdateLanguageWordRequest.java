package com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLanguageWordRequest {
    @NotNull
    private int id;
    @NotNull
    private int wordId;
    @NotNull
    private int languageId;
    @NotNull
    private String translation;
}
