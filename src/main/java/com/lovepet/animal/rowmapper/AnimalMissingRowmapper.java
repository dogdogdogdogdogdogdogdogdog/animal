package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.AnimalMissing;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalMissingRowmapper implements RowMapper<AnimalMissing> {


    @Override
    public AnimalMissing mapRow(ResultSet resultSet, int i) throws SQLException {
        AnimalMissing animalMissing = new AnimalMissing();

        animalMissing.setAnimalId(resultSet.getInt("animal_id"));
        animalMissing.setUserId(resultSet.getInt("user_id"));
        animalMissing.setName(resultSet.getString("name"));
        animalMissing.setKind(resultSet.getString("kind"));
        animalMissing.setVariety(resultSet.getString("variety"));
        animalMissing.setBodysize(resultSet.getString("bodysize"));
        animalMissing.setSex(resultSet.getString("sex"));
        animalMissing.setColor(resultSet.getString("color"));
        animalMissing.setAge(resultSet.getString("age"));
        animalMissing.setDescription(resultSet.getString("description"));
        animalMissing.setImageUrl(resultSet.getString("image_url"));
        animalMissing.setMissingArea(resultSet.getString("missing_area"));
        animalMissing.setMissingDate(resultSet.getDate("missing_date"));
        animalMissing.setCreatedDate(resultSet.getDate("created_date"));

        return animalMissing;
    }
}
