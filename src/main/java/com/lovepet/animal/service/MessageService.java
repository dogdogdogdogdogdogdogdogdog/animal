package com.lovepet.animal.service;

import com.lovepet.animal.dto.ForumData;
import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.model.UserFeedback;

import java.util.List;

public interface MessageService {

    void createMessage(MessageQueryParams messageQueryParams);
    Integer countMessage(MessageQueryParams messageQueryParams);
    List<UserFeedback> getMessage(MessageQueryParams messageQueryParams);


}
