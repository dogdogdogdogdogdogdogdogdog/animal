package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.AnimalHospital;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalHospitalRowmapper implements RowMapper {


    @Override
    public AnimalHospital mapRow(ResultSet resultSet, int i) throws SQLException{
        AnimalHospital animalHospital = new AnimalHospital();
        animalHospital.setId(resultSet.getInt("id"));
        animalHospital.setArea(resultSet.getString("area"));
        animalHospital.setSn(resultSet.getString("sn"));
        animalHospital.setLicense(resultSet.getString("license"));
        animalHospital.setBusiness(resultSet.getString("business"));
        animalHospital.setHospital(resultSet.getString("hospital"));
        animalHospital.setPrincipal(resultSet.getString("principal"));
        animalHospital.setTel(resultSet.getString("tel"));
        animalHospital.setDateOfIssue(resultSet.getString("date_of_issue"));
        animalHospital.setAddress(resultSet.getString("address"));
        return animalHospital;
    }
}
