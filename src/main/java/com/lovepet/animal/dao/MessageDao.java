package com.lovepet.animal.dao;

import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.model.Message;

import java.util.List;

public interface MessageDao {

    void createMessage(MessageQueryParams messageQueryParams);
    List<Message> getMessage(MessageQueryParams messageQueryParams);
    Integer countMessage(MessageQueryParams messageQueryParams);

}
