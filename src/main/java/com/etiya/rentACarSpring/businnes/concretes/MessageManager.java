package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.businnes.abstracts.MessageService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.CreateMessageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.DeleteMessageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.UpdateMessageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.MessageDao;
import com.etiya.rentACarSpring.entities.City;
import com.etiya.rentACarSpring.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageManager implements MessageService {

    MessageDao messageDao;
    ModelMapperService modelMapperService;

    @Autowired
    public MessageManager(MessageDao messageDao, ModelMapperService modelMapperService) {
        this.messageDao = messageDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<Message> getbyId(int messageId) {
        return new SuccesDataResult<Message>(this.messageDao.getById(messageId));
    }

    @Override
    public String getByEnglishMessageByMessageId(int messageId) {
        return this.messageDao.getEnglishMessageByMessageId(messageId);
    }

    @Override
    public String getByTurkishMessageByMessageId(int messageId) {
        return this.messageDao.getTurkishMessageByMessageId(messageId);
    }

    @Override
    public Result save(CreateMessageRequest createMessageRequest) {
        Message message = modelMapperService.forRequest().map(createMessageRequest, Message.class);
        this.messageDao.save(message);
        return new SuccesResult("eklendi");
    }

    @Override
    public Result update(UpdateMessageRequest updateMessageRequest) {
        Message message = modelMapperService.forRequest().map(updateMessageRequest, Message.class);
        this.messageDao.save(message);
        return new SuccesResult("Güncellendi");
    }

    @Override
    public Result delete(DeleteMessageRequest deleteMessageRequest) {
        this.messageDao.deleteById(deleteMessageRequest.getMessageId());
        return new SuccesResult("Silindi");
    }
}
