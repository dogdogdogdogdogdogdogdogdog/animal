package com.lovepet.animal.dao;

import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;

import java.util.List;

public interface PersonalAnimalDao {

    List<PersonalAnimal> getPersonalAnimals(String kind, String sex);

    PersonalAnimal getPersonalAnimalById(Integer personalAnimalId);

    Integer createPersonalAnimal(PersonalAnimalRequest personalAnimalRequest);

    void updatePersonalAnimal(Integer personalAnimalId, PersonalAnimalRequest personalAnimalRequest);

    void deletePersonalAnimalById(Integer personalAnimalId);
}
