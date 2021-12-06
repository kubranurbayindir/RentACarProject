package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.businnes.abstracts.MessageService;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.CreateBrandRequest;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.DeleteBrandRequest;
import com.etiya.rentACarSpring.businnes.request.BrandRequest.UpdateBrandRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.CreateMessageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.DeleteMessageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.UpdateMessageRequest;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/messages")
public class MessagesController {
    private MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateMessageRequest createMessageRequest) {
        return this.messageService.save(createMessageRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateMessageRequest updateMessageRequest) {
        return this.messageService.update(updateMessageRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteMessageRequest deleteMessageRequest) {
        return this.messageService.delete(deleteMessageRequest);
    }


}
