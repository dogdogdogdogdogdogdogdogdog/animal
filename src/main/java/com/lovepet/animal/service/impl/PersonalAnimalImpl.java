package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.PersonalAnimalDao;
import com.lovepet.animal.dto.PersonalAnimalQueryParams;
import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.AnimalFood;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.service.PersonalAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void deletePersonalAnimalById(Integer personalAnimalUserId,Integer personalAnimalId) {
        personalAnimalDao.deletePersonalAnimalById(personalAnimalUserId,personalAnimalId);
    }

    @Override
    public List<String> getPersonalAnimalsComboBox() {
        List<PersonalAnimal> personalAnimalList = personalAnimalDao.getPersonalAnimalComboBox();
        List list = new ArrayList();
        Set<String> areaSet = new HashSet<>();
        Set<String> kindSet = new HashSet<>();
        Set<String> sexSet = new HashSet<>();

        for (int i = 0; i < personalAnimalList.size(); i++) {
            areaSet.add(personalAnimalList.get(i).getArea());
            kindSet.add(personalAnimalList.get(i).getAnimalKind());
            sexSet.add(personalAnimalList.get(i).getAnimalSex());
        }
        System.out.println(areaSet);
        System.out.println(kindSet);
        System.out.println(sexSet);

        list.add(areaSet);
        list.add(kindSet);
        list.add(sexSet);

        return list;
    }
}
