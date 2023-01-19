package com.lovepet.animal.service;

import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;

import java.util.List;

public interface PersonalAnimalService {

    List<PersonalAnimal> getPersonalAnimals();

    PersonalAnimal getPersonalAnimalById(Integer personalAnimalId);

    Integer createPersonalAnimal(PersonalAnimalRequest personalAnimalRequest);

    void updatePersonalAnimal(Integer personalAnimalId, PersonalAnimalRequest personalAnimalRequest);

    void deletePersonalAnimalById(Integer personalAnimalId);
}
