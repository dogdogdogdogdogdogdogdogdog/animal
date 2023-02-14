package com.lovepet.animal.service;

import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.model.Shelter;
import com.lovepet.animal.util.PageContent;

import java.util.List;

public interface AnimalService {

     PageContent getAnimals(AnimalQueryParams animalQueryParams);
    List<Shelter> getShelter();

}
