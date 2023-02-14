package com.lovepet.animal.service;

import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.dto.MessageRequest;
import com.lovepet.animal.model.Message;

import java.util.List;

public interface MessageService {

    void createMessage(MessageQueryParams messageQueryParams);
    Integer countMessage(MessageQueryParams messageQueryParams);
    List<Message> getMessage(MessageQueryParams messageQueryParams);
    Message getMessageById(Integer messageId);
    void updataMessage(Integer messageId, MessageRequest messageRequest);
    void deleteMessageById(Integer articleId, Integer messageId);



}
