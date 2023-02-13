package com.lovepet.animal.dao;

import com.lovepet.animal.dto.PersonalAnimalQueryParams;
import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.AnimalFood;
import com.lovepet.animal.model.PersonalAnimal;

import java.util.List;

public interface PersonalAnimalDao {

    Integer countPersonalAnimal(PersonalAnimalQueryParams personalAnimalQueryParams);

    List<PersonalAnimal> getPersonalAnimals(PersonalAnimalQueryParams personalAnimalQueryParams);

    PersonalAnimal getPersonalAnimalById(Integer personalAnimalId);

    Integer createPersonalAnimal(PersonalAnimalRequest personalAnimalRequest);

    void updatePersonalAnimal(Integer personalAnimalId, PersonalAnimalRequest personalAnimalRequest);

    void deletePersonalAnimalById(Integer personalAnimalUserId,Integer personalAnimalId);
}
