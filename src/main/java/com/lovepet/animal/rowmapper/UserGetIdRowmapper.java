package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGetIdRowmapper implements RowMapper {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user=new User();
        user.setId(resultSet.getInt("user_id"));

        return user;
    }
}
