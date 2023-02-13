package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.PublicAnimalDao;
import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.service.PublicAnimalService;
import com.lovepet.animal.util.PublicPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicAnimalServiceImpl implements PublicAnimalService {
    @Autowired
    PublicAnimalDao publicAnimalDao;

    @Override
    public PublicPage<AnimalData>  getPublicAnimals(AnimalQueryParams animalQueryParams) {
        return publicAnimalDao.getPublicAnimals(animalQueryParams);
    }


}
