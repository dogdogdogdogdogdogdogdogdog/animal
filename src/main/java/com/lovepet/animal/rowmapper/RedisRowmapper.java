package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.Redis;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RedisRowmapper implements RowMapper<Redis> {

    @Override
    public Redis mapRow(ResultSet resultSet, int i) throws SQLException {
        Redis redis = new Redis();

        redis.setLikesId(resultSet.getInt("likes_id"));
        redis.setUserId(resultSet.getString("user_id"));
        redis.setArticleId(resultSet.getString("article_id"));
        redis.setStatus(resultSet.getString("status"));
        return redis;
    }
}
