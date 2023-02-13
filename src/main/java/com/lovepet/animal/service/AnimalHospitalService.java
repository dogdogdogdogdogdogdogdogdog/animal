package com.lovepet.animal.service;

import com.lovepet.animal.dto.AnimalHospitalQueryParams;
import com.lovepet.animal.model.AnimalHospital;

import java.util.List;

public interface AnimalHospitalService {

    Integer countAnimalHospital(AnimalHospitalQueryParams animalHospitalQueryParams);

    List<AnimalHospital> getAnimalHospitals(AnimalHospitalQueryParams animalHospitalQueryParams);

    AnimalHospital getAnimalHospitalById(Integer hospitalId);
}
