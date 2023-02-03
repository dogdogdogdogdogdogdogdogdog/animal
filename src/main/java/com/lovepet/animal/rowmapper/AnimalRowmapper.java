package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.AnimalData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalRowmapper implements RowMapper {
    @Override
    public AnimalData mapRow(ResultSet resultSet, int i) throws SQLException {
        AnimalData animalData = new AnimalData();
        animalData.setAnimalId(resultSet.getInt("animal_id"));
        animalData.setAnimalKind(resultSet.getString("animal_kind"));
        animalData.setAnimalVariety(resultSet.getString("animal_Variety"));
        animalData.setSex(resultSet.getString("animal_sex"));
        animalData.setAnimalBodyType(resultSet.getString("animal_bodytype"));
        animalData.setAnimalColour(resultSet.getString("animal_colour"));
        animalData.setAnimalAge(resultSet.getString("animal_age"));
        animalData.setAnimalSterilization(resultSet.getString("animal_sterilization"));
        animalData.setAnimalBacterin(resultSet.getString("animal_bacterin"));
        animalData.setAnimalFoundplace(resultSet.getString("animal_foundplace"));
        animalData.setAnimalRemark(resultSet.getString("animal_remark"));
        animalData.setAnimalAlbumFile(resultSet.getString("album_file"));
        animalData.setAnimalShelterName(resultSet.getString("shelter_name"));
        animalData.setAnimalShelterAddress(resultSet.getString("shelter_address"));
        animalData.setAnimalShelterTel(resultSet.getString("shelter_tel"));
        animalData.setAnimalOpendate(resultSet.getDate("animal_opendate"));
        animalData.setAnimalUpdate(resultSet.getDate("animal_update"));
        animalData.setAnimalCreatetime(resultSet.getDate("animal_createtime"));

        return animalData;
    }
}
