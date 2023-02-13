package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.UserForum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserForumRowmapper implements RowMapper<UserForum> {


    @Override
    public UserForum mapRow(ResultSet resultSet, int i) throws SQLException {
        UserForum userForum=new UserForum();
        userForum.setTitle(resultSet.getString("title"));
        userForum.setDescription(resultSet.getString("description"));
        userForum.settId(resultSet.getInt("t_id"));
        return userForum;
    }
}
