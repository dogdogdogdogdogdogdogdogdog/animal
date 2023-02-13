package com.lovepet.animal.dao;

import com.lovepet.animal.dto.AnimalHospitalQueryParams;
import com.lovepet.animal.model.AnimalHospital;

import java.util.List;

public interface AnimalHospitalDao{

    Integer countAnimalHospital(AnimalHospitalQueryParams animalHospitalQueryParams);

    List<AnimalHospital> getAnimalHospitals(AnimalHospitalQueryParams animalHospitalQueryParams);

    List<AnimalHospital> getAnimalHospitalsComboBox();
}
