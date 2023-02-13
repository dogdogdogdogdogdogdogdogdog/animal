package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.UserFeedback;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFeedbackRowmapper implements RowMapper<UserFeedback> {

    @Override
    public UserFeedback mapRow(ResultSet resultSet, int i) throws SQLException {
        UserFeedback userFeedback=new UserFeedback();
      userFeedback.setContent(resultSet.getString("content"));
      userFeedback.setPostDate(resultSet.getTimestamp("post_date"));

      return userFeedback;
    }
}
