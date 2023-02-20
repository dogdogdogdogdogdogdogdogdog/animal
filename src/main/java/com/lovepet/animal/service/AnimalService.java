package com.lovepet.animal.service;

import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.util.Page;

public interface AnimalService {

     Page getAnimals(AnimalQueryParams animalQueryParams);

}
