package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.AnimalFoodDao;
import com.lovepet.animal.dto.AnimalFoodQueryParams;
import com.lovepet.animal.model.AnimalFood;
import com.lovepet.animal.service.AnimalFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AnimalFoodServiceImpl implements AnimalFoodService {

    @Autowired
    private AnimalFoodDao animalFoodDao;

    @Override
    public Integer countAnimalFood(AnimalFoodQueryParams animalFoodQueryParams) {
        return animalFoodDao.countAnimalFood(animalFoodQueryParams);
    }

    @Override
    public List<AnimalFood> getAnimalFoods(AnimalFoodQueryParams animalFoodQueryParams) {
        return animalFoodDao.getAnimalFoods(animalFoodQueryParams);
    }

}
