package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.AnimalDao;
import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimalServiceImpl implements AnimalService {

    @Autowired
   private   AnimalDao animalDao;
    @Override
    public List<AnimalData> getAnimals(AnimalQueryParams animalQueryParams) {
         animalQueryParams.setOffset(12*(animalQueryParams.getPage()-1));
        return animalDao.getAnimals(animalQueryParams);
    }

    @Override
    public List<Integer> getPages(AnimalQueryParams animalQueryParams) {
        return animalDao.getPages(animalQueryParams);
    }
}
