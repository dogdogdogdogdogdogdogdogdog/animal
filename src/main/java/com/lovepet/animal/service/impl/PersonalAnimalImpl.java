package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.PersonalAnimalDao;
import com.lovepet.animal.dto.PersonalAnimalQueryParams;
import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.service.PersonalAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonalAnimalImpl implements PersonalAnimalService {

    @Autowired
    private PersonalAnimalDao personalAnimalDao;

    @Override
    public Integer countPersonalAnimal(PersonalAnimalQueryParams personalAnimalQueryParams) {
        return personalAnimalDao.countPersonalAnimal(personalAnimalQueryParams);
    }

    @Override
    public List<PersonalAnimal> getPersonalAnimals(PersonalAnimalQueryParams personalAnimalQueryParams) {
        return personalAnimalDao.getPersonalAnimals(personalAnimalQueryParams);
    }

    @Override
    public PersonalAnimal getPersonalAnimalById(Integer personalAnimalId) {
        return personalAnimalDao.getPersonalAnimalById(personalAnimalId);
    }

    @Override
    public Integer createPersonalAnimal(PersonalAnimalRequest personalAnimalRequest) {
        return personalAnimalDao.createPersonalAnimal(personalAnimalRequest);
    }

    @Override
    public void updatePersonalAnimal(Integer personalAnimalId, PersonalAnimalRequest personalAnimalRequest) {
        personalAnimalDao.updatePersonalAnimal(personalAnimalId, personalAnimalRequest);
    }

    @Override
    public void deletePersonalAnimalById(Integer personalAnimalId) {
        personalAnimalDao.deletePersonalAnimalById(personalAnimalId);
    }
}
