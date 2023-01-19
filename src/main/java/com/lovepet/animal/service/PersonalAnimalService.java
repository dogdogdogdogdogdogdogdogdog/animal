package com.lovepet.animal.service;

import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;

public interface PersonalAnimalService {
    PersonalAnimal getPersonalAnimalById(Integer personalAnimalId);

    Integer createPersonalAnimal(PersonalAnimalRequest personalAnimalRequest);

}
