package com.lovepet.animal.service;


import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.util.PublicPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


public interface PublicAnimalService {

      PublicPage<AnimalData>  getPublicAnimals(AnimalQueryParams animalQueryParams);
}
