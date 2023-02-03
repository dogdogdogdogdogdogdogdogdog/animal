package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.AnimalFood;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalFoodRowmapper implements RowMapper {

    @Override
    public AnimalFood mapRow(ResultSet resultSet, int i) throws SQLException {
        AnimalFood animalFood = new AnimalFood();
        animalFood.setSerialNumber(resultSet.getString("serial_number"));
        animalFood.setName(resultSet.getString("name"));
        animalFood.setCategory(resultSet.getString("category"));
        animalFood.setSource(resultSet.getString("source"));
        animalFood.setWeight(resultSet.getString("weight"));
        animalFood.setMaterial(resultSet.getString("material"));
        animalFood.setNutrient(resultSet.getString("nutrient"));
        animalFood.setSuitable(resultSet.getString("suitable"));
        animalFood.setInstruction(resultSet.getString("instruction"));
        animalFood.setPreservation(resultSet.getString("preservation"));
        animalFood.setProductionPlace(resultSet.getString("production_place"));
        animalFood.setCompany(resultSet.getString("company"));
        return animalFood;
    }

}
