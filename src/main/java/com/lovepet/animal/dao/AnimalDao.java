package com.lovepet.animal.dao;

import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.util.Page;

public interface AnimalDao {
  Page getAnimals(AnimalQueryParams animalQueryParams);




}
