package com.lovepet.animal.dao;

import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;

public interface PersonalAnimalDao {

    PersonalAnimal getPersonalAnimalById(Integer personalAnimalId);

    Integer createPersonalAnimal(PersonalAnimalRequest personalAnimalRequest);

    void updatePersonalAnimal(Integer personalAnimalId, PersonalAnimalRequest personalAnimalRequest);
}
