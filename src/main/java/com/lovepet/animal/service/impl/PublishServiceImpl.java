package com.lovepet.animal.service.impl;


import com.lovepet.animal.dao.PublishDao;
import com.lovepet.animal.dto.PublishAnimalRequest;
import com.lovepet.animal.model.PublishData;
import com.lovepet.animal.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublishServiceImpl implements PublishService {

    @Autowired
    private PublishDao publishDao;

    @Override
    public void createPublish(PublishAnimalRequest publishAnimalRequest) {

        publishDao.createPublish(publishAnimalRequest);
    }

    @Override
    public void updatePublish(PublishAnimalRequest publishAnimalRequest) {

       publishDao.updatePublish(publishAnimalRequest);
    }

    @Override
    public List<PublishData> getPublishById(Integer id) {
        return publishDao.getPublishById(id);
    }

    @Override
    public void delPublish(Integer userId, Integer item) {
         publishDao.delPublish(userId,item);
    }
}
