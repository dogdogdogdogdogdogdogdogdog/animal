package com.lovepet.animal.dao;

import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.util.PublicPage;

import java.util.List;

public interface PublicAnimalDao {


    PublicPage<AnimalData>   getPublicAnimals(AnimalQueryParams animalQueryParams);

}
