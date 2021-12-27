package com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteLanguageWordRequest {
    @NotNull
    private int id;
}
