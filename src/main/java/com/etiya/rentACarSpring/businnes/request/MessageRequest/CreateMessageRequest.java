package com.etiya.rentACarSpring.businnes.request.MessageRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequest {
   @JsonIgnore
    private int messageId;

    private String turkish;

    private String english;
}
