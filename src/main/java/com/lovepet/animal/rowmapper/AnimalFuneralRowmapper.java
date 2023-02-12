package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.AnimalFuneral;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalFuneralRowmapper implements RowMapper {

    @Override
    public AnimalFuneral mapRow(ResultSet resultSet, int i) throws SQLException {
        AnimalFuneral animalFuneral = new AnimalFuneral();
        animalFuneral.setId(resultSet.getInt("id"));
        animalFuneral.setAgent(resultSet.getString("agent"));
        animalFuneral.setTel(resultSet.getString("tel"));
        animalFuneral.setArea(resultSet.getString("area"));
        animalFuneral.setAddress(resultSet.getString("address"));
        animalFuneral.setLink(resultSet.getString("link"));
        animalFuneral.setService(resultSet.getString("service"));

        return animalFuneral;
    }
}
