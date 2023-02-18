package com.lovepet.animal.dao;

import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.dto.MessageRequest;
import com.lovepet.animal.model.Message;

import java.util.List;

public interface MessageDao {

    void createMessage(MessageQueryParams messageQueryParams);
    List<Message> getMessage(MessageQueryParams messageQueryParams);
    Integer countMessage(MessageQueryParams messageQueryParams);
    Message getMessageById(Integer messageId);
    void updataMessage(Integer messageId, MessageRequest messageRequest);
    void deleteMessageById(Integer articleId, Integer messageId);


}
