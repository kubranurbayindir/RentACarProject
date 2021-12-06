package com.etiya.rentACarSpring.businnes.request.MessageRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMessageRequest {
    private int messageId;

    private String turkish;

    private String english;
}
