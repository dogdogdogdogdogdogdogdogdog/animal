package com.lovepet.animal.service;

import com.lovepet.animal.dto.AnimalFoodQueryParams;
import com.lovepet.animal.model.AnimalFood;

import java.util.List;
import java.util.Set;

public interface AnimalFoodService {

    Integer countAnimalFood(AnimalFoodQueryParams animalFoodQueryParams);

    List<AnimalFood> getAnimalFoods(AnimalFoodQueryParams animalFoodQueryParams);

    Set<String> getProductionPlaceSet(AnimalFoodQueryParams animalFoodQueryParams);

    Set<String> getCategorySet(AnimalFoodQueryParams animalFoodQueryParams);
}
