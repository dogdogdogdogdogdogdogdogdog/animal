package com.lovepet.animal.dao;

import com.lovepet.animal.dto.AnimalFoodQueryParams;
import com.lovepet.animal.model.AnimalFood;

import java.util.List;

public interface AnimalFoodDao {

    Integer countAnimalFood(AnimalFoodQueryParams animalFoodQueryParams);

    List<AnimalFood> getAnimalFoods(AnimalFoodQueryParams animalFoodQueryParams);


}
