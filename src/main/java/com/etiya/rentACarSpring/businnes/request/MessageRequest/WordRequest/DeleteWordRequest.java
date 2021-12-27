package com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteWordRequest {
    @NotNull
    private int wordId;
}
