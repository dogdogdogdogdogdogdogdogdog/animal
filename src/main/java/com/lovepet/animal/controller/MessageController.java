package com.lovepet.animal.controller;


import com.lovepet.animal.dto.ForumArticleRequest;
import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.dto.MessageRequest;
import com.lovepet.animal.model.ForumArticle;
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

    @GetMapping("/message")//取得所有留言
    public ResponseEntity<List<Message>> getMessage(
            @RequestParam(required = false) Integer articleId
    ) {
        MessageQueryParams messageQueryParams = new MessageQueryParams();
        messageQueryParams.setArticleId(articleId);

        List list = new ArrayList();

        List<Message> messages = messageService.getMessage(messageQueryParams);

        Integer total = messageService.countMessage(messageQueryParams);

        list.add(messages);
        list.add(total);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/message/{messageId}")//查詢指定留言id(單筆)
    public ResponseEntity<Message> getForumArticle(@PathVariable Integer messageId) {
        Message message = messageService.getMessageById(messageId);

        if (message != null) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/message/{messageId}")//編輯留言
    public ResponseEntity<Message> updateForumArticle(@PathVariable Integer messageId,
                                                      @RequestBody @Valid MessageRequest messageRequest) {
        //檢查messageId 是否存在
        Message message = messageService.getMessageById(messageId);

        if (message == null) {//找不到回傳404 NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改文章內容
        messageService.updataMessage(messageId, messageRequest);

        Message updatedMessage = messageService.getMessageById(messageId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedMessage);
    }

}
