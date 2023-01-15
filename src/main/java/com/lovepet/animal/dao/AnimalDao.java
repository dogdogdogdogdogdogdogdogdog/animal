package com.lovepet.animal.dao;

import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;

import java.util.List;

public interface AnimalDao {
    public List<AnimalData> getAnimals(AnimalQueryParams animalQueryParams);
    public List<Integer> getPages(AnimalQueryParams animalQueryParams);
}
