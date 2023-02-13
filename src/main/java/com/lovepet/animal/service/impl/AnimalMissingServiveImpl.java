package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.AnimalMissingDao;
import com.lovepet.animal.dto.AnimalMissingQueryParams;
import com.lovepet.animal.dto.AnimalMissingRequest;
import com.lovepet.animal.model.AnimalMissing;
import com.lovepet.animal.service.AnimalMissingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalMissingServiveImpl implements AnimalMissingService {

    @Autowired
    private AnimalMissingDao animalMissingDao;

    @Override
    public Integer countAnimalMissing(AnimalMissingQueryParams animalMissingQueryParams){
        return animalMissingDao.countAnimalMissing(animalMissingQueryParams);
    }

    @Override
    public List<AnimalMissing> getAnimalsMissing(AnimalMissingQueryParams animalMissingQueryParams){
        return animalMissingDao.getAnimalsMissing(animalMissingQueryParams);
    }

    @Override
    public AnimalMissing getAnimalMissingById(Integer animalMissingId){
        return animalMissingDao.getAnimalMissingById(animalMissingId);
    }

    @Override
    public Integer createAnimalMissing(AnimalMissingRequest animalMissingRequest){
        return animalMissingDao.createAnimalMissing(animalMissingRequest);
    }

    @Override
    public void updateAnimalMissing(Integer animalMissingId, AnimalMissingRequest animalMissingRequest){
        animalMissingDao.updateAnimalMissing(animalMissingId, animalMissingRequest);

    }

    @Override
    public void deleteAnimalMissingById(Integer animalMissingUserId,Integer animalMissingId){
        animalMissingDao.deleteAnimalMissingById(animalMissingUserId, animalMissingId);
    }

    @Override
    public List<String> getAnimalsMissingComboBox() {
        List<AnimalMissing> animalMissingList = animalMissingDao.getAnimalsMissingComboBox()
                ;
        List list = new ArrayList();
        Set<String> areaSet = new HashSet<>();
        Set<String> kindSet = new HashSet<>();
        Set<String> sexSet = new HashSet<>();

        for (int i = 0; i < animalMissingList.size(); i++) {
            areaSet.add(animalMissingList.get(i).getMissingArea());
            kindSet.add(animalMissingList.get(i).getKind());
            sexSet.add(animalMissingList.get(i).getSex());
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

