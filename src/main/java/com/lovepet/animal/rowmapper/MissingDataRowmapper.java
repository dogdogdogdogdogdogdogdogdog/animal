package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.MissingData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MissingDataRowmapper implements RowMapper {
    @Override
    public MissingData mapRow(ResultSet resultSet, int i) throws SQLException {
        MissingData missingData = new MissingData();
        missingData.setKind(resultSet.getString("kind"));
        missingData.setVariety(resultSet.getString("variety"));
        missingData.setSex(resultSet.getString("sex"));
        missingData.setBodyShape(resultSet.getString("body_shape"));
        missingData.setColor(resultSet.getString("color"));
        missingData.setAge(resultSet.getString("age"));
        missingData.setMissingDate(resultSet.getDate("missing_date"));
        missingData.setMissingPlace(resultSet.getString("missing_place"));
        missingData.setRemark(resultSet.getString("remark"));
        missingData.setPhotoUrl(resultSet.getString("photo_url"));


        return missingData;
    }
}
