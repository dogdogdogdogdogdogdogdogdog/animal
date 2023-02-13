package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowmapper implements RowMapper {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setTel(resultSet.getString("tel"));
        user.setGender(resultSet.getString("gender"));
        user.setCreatedDate(resultSet.getDate("created_date"));
        user.setLastModifiedDate(resultSet.getDate("last_modified_date"));
        user.setAvatar(resultSet.getString("avatar"));
        return user;
    }
}
