package com.lovepet.animal.service;

import com.lovepet.animal.dto.PublishAnimalRequest;
import com.lovepet.animal.model.PublishData;

import java.util.List;

public interface PublishService {

    public void createPublish(PublishAnimalRequest publishAnimalRequest);

    public void updatePublish(PublishAnimalRequest publishAnimalRequest);

    public List<PublishData> getPublishById(Integer id);

    public void delPublish(Integer userId, Integer item);


}
