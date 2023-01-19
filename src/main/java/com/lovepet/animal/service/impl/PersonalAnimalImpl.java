package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.PersonalAnimalDao;
import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.service.PersonalAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonalAnimalImpl implements PersonalAnimalService {

    @Autowired
    private PersonalAnimalDao personalAnimalDao;
    @Override
    public PersonalAnimal getPersonalAnimalById(Integer personalAnimalId) {
        return personalAnimalDao.getPersonalAnimalById(personalAnimalId);
    }

    @Override
    public Integer createPersonalAnimal(PersonalAnimalRequest personalAnimalRequest) {
        return personalAnimalDao.createPersonalAnimal(personalAnimalRequest);
    }
}
