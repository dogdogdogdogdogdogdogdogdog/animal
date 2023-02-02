package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.Shelter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShelterRowmapper implements RowMapper {


    @Override
    public Shelter mapRow(ResultSet resultSet, int i) throws SQLException {
        Shelter shelter = new Shelter();
        shelter.setName(resultSet.getString("shelter_name"));

        return shelter;
    }
}
