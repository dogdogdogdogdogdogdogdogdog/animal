package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.AnimalHospitalDao;
import com.lovepet.animal.dto.AnimalHospitalQueryParams;
import com.lovepet.animal.model.AnimalHospital;
import com.lovepet.animal.rowmapper.AnimalHospitalRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimalHospitalDaoImpl implements AnimalHospitalDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countAnimalHospital(AnimalHospitalQueryParams animalHospitalQueryParams) {
        return null;
    }

    @Override
    public List<AnimalHospital> getAnimalHospitals(AnimalHospitalQueryParams animalHospitalQueryParams) {
        return null;
    }

    @Override
    public List<AnimalHospital> getAnimalHospitalsComboBox(){
        String sql = "SELECT * FROM animal_hospital WHERE 1=1";

        List<AnimalHospital> list = namedParameterJdbcTemplate.query(sql , new AnimalHospitalRowmapper());
        return list;
    }
}
