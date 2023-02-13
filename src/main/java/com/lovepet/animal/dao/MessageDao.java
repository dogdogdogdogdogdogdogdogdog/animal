package com.lovepet.animal.dao;

import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.model.UserFeedback;

import java.util.List;

public interface MessageDao {

    void createMessage(MessageQueryParams messageQueryParams);
    List<UserFeedback> getMessage(MessageQueryParams messageQueryParams);
    Integer countMessage(MessageQueryParams messageQueryParams);

}
