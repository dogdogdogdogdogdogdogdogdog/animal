package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.AnimalFoodDao;
import com.lovepet.animal.dto.AnimalFoodQueryParams;
import com.lovepet.animal.model.AnimalFood;
import com.lovepet.animal.service.AnimalFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public Set<String> getProductionPlaceSet() {
        List<AnimalFood> list = animalFoodDao.getAnimalFoodsComboBox();
        HashSet hashSet = new HashSet<>();

        for (int i = 0; i < list.size(); i++) {
            hashSet.add(list.get(i).getCategory());
        }
        hashSet.remove("");
        System.out.println(hashSet);
        return hashSet;
    }

    @Override
    public Set<String> getCategorySet() {
        List<AnimalFood> list = animalFoodDao.getAnimalFoodsComboBox();
        HashSet hashSet = new HashSet<>();

        for (int i = 0; i < list.size(); i++) {
            hashSet.add(list.get(i).getProductionPlace());
        }
        System.out.println(hashSet);
        return hashSet;
    }
}
