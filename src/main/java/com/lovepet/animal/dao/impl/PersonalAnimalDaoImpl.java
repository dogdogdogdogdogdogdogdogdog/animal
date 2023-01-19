package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.PersonalAnimalDao;
import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.rowmapper.PersonalAnimalRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PersonalAnimalDaoImpl implements PersonalAnimalDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public PersonalAnimal getPersonalAnimalById(Integer personalAnimalId) {
        String sql = "SELECT animal_id, animal_kind, animal_variety, animal_sex, animal_sterilization, animal_bacterin, " +
                "image_url, description, created_date, last_modified_date " +
                "FROM personal_shelter WHERE animal_id = :personalAnimalId";

        Map<String, Object> map = new HashMap<>();
        map.put("personalAnimalId", personalAnimalId);

        List<PersonalAnimal> personalAnimalList = namedParameterJdbcTemplate.query(sql, map, new PersonalAnimalRowmapper());

        if (personalAnimalList.size()>0) {
            return personalAnimalList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createPersonalAnimal(PersonalAnimalRequest personalAnimalRequest) {
        String sql = "INSERT INTO personal_shelter(animal_kind, animal_variety, animal_sex, animal_sterilization, animal_bacterin, image_url, description, created_date, last_modified_date) " +
                "VALUES (:animalKind, :animalVariety, :animalSex, :animalSterilization, :animalBacterin, :imageUrl, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("animalKind", personalAnimalRequest.getAnimalKind());
        map.put("animalVariety", personalAnimalRequest.getAnimalVariety());
        map.put("animalSex", personalAnimalRequest.getAnimalSex());
        map.put("animalSterilization", personalAnimalRequest.getAnimalSterilization());
        map.put("animalBacterin", personalAnimalRequest.getAnimalBacterin());
        map.put("imageUrl", personalAnimalRequest.getImageUrl());
        map.put("description", personalAnimalRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int personalAnimalId = keyHolder.getKey().intValue();

        return personalAnimalId;
    }
}
