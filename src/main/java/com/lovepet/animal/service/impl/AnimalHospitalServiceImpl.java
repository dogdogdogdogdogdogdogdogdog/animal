package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.AnimalHospitalDao;
import com.lovepet.animal.dto.AnimalHospitalQueryParams;
import com.lovepet.animal.model.AnimalHospital;
import com.lovepet.animal.service.AnimalHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AnimalHospitalServiceImpl implements AnimalHospitalService {

    @Autowired
    private AnimalHospitalDao animalHospitalDao;

    @Override
    public  Integer countAnimalHospital(AnimalHospitalQueryParams animalHospitalQueryParams){
        return animalHospitalDao.countAnimalHospital(animalHospitalQueryParams);
    }

    @Override
    public List<AnimalHospital> getAnimalHospitals(AnimalHospitalQueryParams animalHospitalQueryParams) {
        return animalHospitalDao.getAnimalHospitals(animalHospitalQueryParams);
    }

    public List<String> getAnimalHospitalsComboBox(){
        List<AnimalHospital> animalHospitalList = animalHospitalDao.getAnimalHospitalsComboBox();
        List list = new ArrayList();
        Set<String> areaSet = new HashSet<>();

        for(int i =0; i<animalHospitalList.size(); i++){
            areaSet.add(animalHospitalList.get(i).getArea());
        }
        areaSet.remove("");
        System.out.println(areaSet);

        list.add(areaSet);

        return list;
    }
}
