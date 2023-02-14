package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.MessageDao;
import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.model.UserFeedback;
import com.lovepet.animal.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    @Override
    public void createMessage(MessageQueryParams messageQueryParams) {
        messageDao.createMessage(messageQueryParams);
    }

    @Override
    public Integer countMessage(MessageQueryParams messageQueryParams) {
        return messageDao.countMessage(messageQueryParams);
    }

    @Override
    public List<UserFeedback> getMessage(MessageQueryParams messageQueryParams) {
        return messageDao.getMessage(messageQueryParams);
    }
}
