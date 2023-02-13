package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.AnimalFoodDao;
import com.lovepet.animal.dto.AnimalFoodQueryParams;
import com.lovepet.animal.dto.PersonalAnimalQueryParams;
import com.lovepet.animal.model.AnimalFood;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.rowmapper.AnimalFoodRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimalFoodDaoImpl implements AnimalFoodDao {

    @Autowired
    private NamedParameterJdbcTemplate animalJdbcTemplate;


    @Override
    public List<AnimalFood> getAnimalFoodsComboBox() {
        String sql = "SELECT * FROM animal_food WHERE 1=1";

        List<AnimalFood> list = animalJdbcTemplate.query(sql, new AnimalFoodRowmapper());
        return list;
    }

    @Override
    public Integer countAnimalFood(AnimalFoodQueryParams animalFoodQueryParams) {
        String sql = "SELECT count(*) FROM animal_food WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, animalFoodQueryParams);

        // 將 count 值轉換為 Integer 類型的返回值
        Integer total = animalJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<AnimalFood> getAnimalFoods(AnimalFoodQueryParams animalFoodQueryParams) {
        String sql = "SELECT serial_number, name, category, source, weight, material, nutrient, " +
                "suitable, instruction, preservation, production_place, company FROM animal_food WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, animalFoodQueryParams);

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", animalFoodQueryParams.getLimit());
        map.put("offset", animalFoodQueryParams.getOffset());

        List<AnimalFood> animalFoodList = animalJdbcTemplate.query(sql, map, new AnimalFoodRowmapper());

        return animalFoodList;
    }

    private String addFilteringSql(String sql, Map<String, Object> map, AnimalFoodQueryParams animalFoodQueryParams) {
        if (animalFoodQueryParams.getProductionPlace() != null) {
            sql = sql + " AND production_place = :productionPlace";
            map.put("productionPlace", animalFoodQueryParams.getProductionPlace());
        }

        if (animalFoodQueryParams.getCategory() != null) {
            sql = sql + " AND category = :category";
            map.put("category", animalFoodQueryParams.getCategory());
        }

        if (animalFoodQueryParams.getSearch() !=null) {
            sql = sql + " AND name LIKE :search";
            map.put("search", "%" + animalFoodQueryParams.getSearch() + "%");
        }

        return sql;
    }

}
