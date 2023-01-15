package com.lovepet.animal.dao;

import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.model.Shelter;
import com.lovepet.animal.util.PageContent;

import java.util.List;

public interface AnimalDao {
    public PageContent getAnimals(AnimalQueryParams animalQueryParams);

    public List<Shelter> getShelter();
}
