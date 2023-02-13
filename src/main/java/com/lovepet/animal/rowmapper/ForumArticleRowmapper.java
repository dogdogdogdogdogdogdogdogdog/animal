package com.lovepet.animal.rowmapper;

import com.lovepet.animal.model.ForumArticle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForumArticleRowmapper implements RowMapper<ForumArticle> {

    @Override
    public ForumArticle mapRow(ResultSet resultSet, int i) throws SQLException {
        ForumArticle forumArticle = new ForumArticle();

        forumArticle.setArticleId(resultSet.getInt("article_id"));
        forumArticle.setUserId(resultSet.getInt("user_id"));
        forumArticle.setType(resultSet.getString("type"));
        forumArticle.setTitle(resultSet.getString("title"));
        forumArticle.setContent(resultSet.getString("content"));
        forumArticle.setViews(resultSet.getString("views"));
        forumArticle.setLikes(resultSet.getString("likes"));
        forumArticle.setPostDate(resultSet.getDate("post_date"));
        forumArticle.setModifiedDate(resultSet.getDate("modified_date"));

        return forumArticle;
    }
}
