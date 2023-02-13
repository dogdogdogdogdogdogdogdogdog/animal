package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.UserFeedback;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFeedbackRowmapper implements RowMapper<UserFeedback> {

    @Override
    public UserFeedback mapRow(ResultSet resultSet, int i) throws SQLException {
        UserFeedback userFeedback=new UserFeedback();
        userFeedback.setMessage(resultSet.getString("message"));
        userFeedback.setCreateDate(resultSet.getTimestamp("create_date"));
        userFeedback.setEmail(resultSet.getString("email"));
        return userFeedback;
    }
}
