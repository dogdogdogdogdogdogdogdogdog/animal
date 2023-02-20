package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.AnimalDao;
import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.service.AnimalService;
import com.lovepet.animal.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalDao animalDao;

    @Override
    public Page getAnimals(AnimalQueryParams animalQueryParams) {
        return animalDao.getAnimals(animalQueryParams);
    }



}
