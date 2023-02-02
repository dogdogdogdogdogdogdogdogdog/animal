package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.PersonalAnimalDao;
import com.lovepet.animal.dto.PersonalAnimalQueryParams;
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
    public Integer countPersonalAnimal(PersonalAnimalQueryParams personalAnimalQueryParams) {
        String sql = "SELECT count(*) FROM personal_animal WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, personalAnimalQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<PersonalAnimal> getPersonalAnimals(PersonalAnimalQueryParams personalAnimalQueryParams) {
//        String sql = "SELECT animal_id, animal_kind, animal_variety, animal_sex, animal_sterilization, animal_bacterin, " +
//                "image_url, description, created_date, last_modified_date " +
//                "FROM personal_animal WHERE 1=1";
        String sql = "SELECT * FROM personal_animal WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, personalAnimalQueryParams);

        //排序
        sql = sql + " ORDER BY " + personalAnimalQueryParams.getOrderBy() + " " + personalAnimalQueryParams.getSort();

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", personalAnimalQueryParams.getLimit());
        map.put("offset", personalAnimalQueryParams.getOffset());

        List<PersonalAnimal> personalAnimalList = namedParameterJdbcTemplate.query(sql, map, new PersonalAnimalRowmapper());

        return personalAnimalList;
    }

    @Override
    public PersonalAnimal getPersonalAnimalById(Integer personalAnimalId) {
//        String sql = "SELECT animal_id, animal_kind, animal_variety, animal_sex, animal_sterilization, animal_bacterin, " +
//                "image_url, description, created_date, last_modified_date " +
//                "FROM personal_animal WHERE animal_id = :personalAnimalId";
        String sql = "SELECT * FROM personal_animal WHERE animal_id = :personalAnimalId";

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
        String sql = "INSERT INTO personal_animal(animal_name, animal_kind, animal_variety, animal_sex, animal_sterilization, animal_bacterin, image_url, area, phone, description, created_date, last_modified_date) " +
                "VALUES (:animalName, :animalKind, :animalVariety, :animalSex, :animalSterilization, :animalBacterin, :imageUrl, :area, :phone, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("animalName", personalAnimalRequest.getAnimalName());
        map.put("animalKind", personalAnimalRequest.getAnimalKind());
        map.put("animalVariety", personalAnimalRequest.getAnimalVariety());
        map.put("animalSex", personalAnimalRequest.getAnimalSex());
        map.put("animalSterilization", personalAnimalRequest.getAnimalSterilization());
        map.put("animalBacterin", personalAnimalRequest.getAnimalBacterin());
        map.put("area", personalAnimalRequest.getArea());
        map.put("phone", personalAnimalRequest.getPhone());
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

    @Override
    public void updatePersonalAnimal(Integer personalAnimalId, PersonalAnimalRequest personalAnimalRequest) {
        String sql = "UPDATE personal_animal SET animal_name = :animalName, animal_kind = :animalKind, animal_variety = :animalVariety, animal_sex = :animalSex, " +
                "animal_sterilization = :animalSterilization, animal_bacterin = :animalBacterin, area = :area, phone = :phone, image_url = :imageUrl, description = :description, " +
                "last_modified_date = :lastModifiedDate WHERE animal_id = :animalId";

        Map<String, Object> map = new HashMap<>();
        map.put("animalId", personalAnimalId);
        map.put("animalName", personalAnimalRequest.getAnimalName());
        map.put("animalKind", personalAnimalRequest.getAnimalKind());
        map.put("animalVariety", personalAnimalRequest.getAnimalVariety());
        map.put("animalSex", personalAnimalRequest.getAnimalSex());
        map.put("animalSterilization", personalAnimalRequest.getAnimalSterilization());
        map.put("animalBacterin", personalAnimalRequest.getAnimalBacterin());
        map.put("area", personalAnimalRequest.getArea());
        map.put("phone", personalAnimalRequest.getPhone());
        map.put("description", personalAnimalRequest.getDescription());

        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deletePersonalAnimalById(Integer personalAnimalId) {
        String sql = "DELETE FROM personal_animal WHERE animal_id = :personalAnimalId";

        Map<String, Object> map = new HashMap<>();
        map.put("personalAnimalId", personalAnimalId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    private String addFilteringSql(String sql, Map<String, Object> map, PersonalAnimalQueryParams personalAnimalQueryParams) {
        if (personalAnimalQueryParams.getKind() !=null) {
            sql = sql + " AND animal_kind = :animalKind";
            map.put("animalKind", personalAnimalQueryParams.getKind());
        }

        if (personalAnimalQueryParams.getSex() !=null) {
            sql = sql + " AND animal_sex = :animalSex";
            map.put("animalSex", personalAnimalQueryParams.getSex());
        }

        return sql;
    }

}
