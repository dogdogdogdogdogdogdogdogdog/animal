package com.lovepet.animal.controller;


import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.model.Message;
import com.lovepet.animal.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity createMessage(@RequestBody @Valid MessageQueryParams messageQueryParams) {

        messageService.createMessage(messageQueryParams);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/message/{tId}")
    public ResponseEntity<List<Message>> getMessage(@PathVariable Integer tId) {
        MessageQueryParams messageQueryParams = new MessageQueryParams();
        messageQueryParams.setArticleId(tId);

        List list = new ArrayList();

        Integer total = messageService.countMessage(messageQueryParams);

        List<Message> messages = messageService.getMessage(messageQueryParams);

        list.add(messages);
        list.add(total);


        if (messages != null) {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
