package com.lovepet.animal.service;

import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;

import java.util.List;

public interface AnimalService {
    public List<AnimalData> getAnimals(AnimalQueryParams animalQueryParams);
    public List<Integer> getPages(AnimalQueryParams animalQueryParams);

}
