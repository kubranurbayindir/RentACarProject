package com.etiya.rentACarSpring.businnes.abstracts;


import com.etiya.rentACarSpring.businnes.request.MessageRequest.CreateMessageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.DeleteMessageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.UpdateMessageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.Message;

public interface MessageService {
    DataResult<Message> getbyId(int messageId);
    String getByEnglishMessageByMessageId(int messageId);
    String getByTurkishMessageByMessageId(int messageId);

    Result save(CreateMessageRequest createMessageRequest);

    Result update(UpdateMessageRequest updateMessageRequest);

    Result delete(DeleteMessageRequest deleteMessageRequest);
}
