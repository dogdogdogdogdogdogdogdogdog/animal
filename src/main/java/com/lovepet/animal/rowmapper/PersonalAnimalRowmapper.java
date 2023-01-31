package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.PersonalAnimal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalAnimalRowmapper implements RowMapper<PersonalAnimal> {
    @Override
    public PersonalAnimal mapRow(ResultSet resultSet, int i) throws SQLException {
        PersonalAnimal personalAnimal = new PersonalAnimal();

        personalAnimal.setAnimalId(resultSet.getInt("animal_id"));
        personalAnimal.setAnimalName(resultSet.getString("animal_name"));
        personalAnimal.setAnimalKind(resultSet.getString("animal_kind"));
        personalAnimal.setAnimalVariety(resultSet.getString("animal_variety"));
        personalAnimal.setAnimalSex(resultSet.getString("animal_sex"));
        personalAnimal.setAnimalSterilization(resultSet.getString("animal_sterilization"));
        personalAnimal.setAnimalBacterin(resultSet.getString("animal_bacterin"));
        personalAnimal.setArea(resultSet.getString("area"));
        personalAnimal.setPhone(resultSet.getString("phone"));
        personalAnimal.setImageUrl(resultSet.getString("image_url"));
        personalAnimal.setDescription(resultSet.getString("description"));
        personalAnimal.setCreatedDate(resultSet.getDate("created_date"));
        personalAnimal.setLastModifiedDate(resultSet.getDate("last_modified_date"));

        return personalAnimal;
    }
}
