package com.lovepet.animal.service;

import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.model.Message;

import java.util.List;

public interface MessageService {

    void createMessage(MessageQueryParams messageQueryParams);
    Integer countMessage(MessageQueryParams messageQueryParams);
    List<Message> getMessage(MessageQueryParams messageQueryParams);


}
