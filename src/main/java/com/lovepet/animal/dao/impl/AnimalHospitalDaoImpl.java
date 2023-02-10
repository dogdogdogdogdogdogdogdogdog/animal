package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.AnimalHospitalDao;
import com.lovepet.animal.dto.AnimalHospitalQueryParams;
import com.lovepet.animal.model.AnimalHospital;
import com.lovepet.animal.rowmapper.AnimalHospitalRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimalHospitalDaoImpl implements AnimalHospitalDao {

    @Autowired
    private NamedParameterJdbcTemplate animalJdbcTemplate;

    @Override
    public List<AnimalHospital> getAnimalHospitalsComboBox(){
        String sql = "SELECT * FROM animal_hospital WHERE 1=1";

        List<AnimalHospital> list = animalJdbcTemplate.query(sql , new AnimalHospitalRowmapper());
        return list;
    }

    @Override
    public Integer countAnimalHospital(AnimalHospitalQueryParams animalHospitalQueryParams) {
        String sql = "SELECT count(*) FROM animal_hospital WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, animalHospitalQueryParams);

        //將 count 值轉換為 Integer 類型的返回值
        Integer total = animalJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<AnimalHospital> getAnimalHospitals(AnimalHospitalQueryParams animalHospitalQueryParams) {
        String sql = "SELECT area, sn, license, business, hospital, principal, " +
                "tel, date_of_issue, address FROM animal_hospital WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, animalHospitalQueryParams);

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", animalHospitalQueryParams.getLimit());
        map.put("offset", animalHospitalQueryParams.getOffset());

        List<AnimalHospital> animalHospitalList = animalJdbcTemplate.query(sql, map, new AnimalHospitalRowmapper());

        return animalHospitalList;
    }

    private String addFilteringSql(String sql, Map<String, Object> map, AnimalHospitalQueryParams animalHospitalQueryParams) {
        if(animalHospitalQueryParams.getArea() != null){
            sql = sql + " AND area = :area";
            map.put("area", animalHospitalQueryParams.getArea());
        }
        if (animalHospitalQueryParams.getSearch() !=null) {
            sql = sql + " AND hospital LIKE :search";
            map.put("search", "%" + animalHospitalQueryParams.getSearch() + "%");
        }
        return  sql;
    }

}
