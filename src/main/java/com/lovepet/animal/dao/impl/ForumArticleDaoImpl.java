package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.ForumArticleDao;
import com.lovepet.animal.dto.ForumArticleQueryParams;
import com.lovepet.animal.dto.PersonalAnimalQueryParams;
import com.lovepet.animal.model.ForumArticle;
import com.lovepet.animal.rowmapper.ForumArticleRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ForumArticleDaoImpl implements ForumArticleDao {

    @Autowired
    @Qualifier("animalJdbcTemplate")
    private NamedParameterJdbcTemplate animalJdbcTemplate;
    @Autowired
    @Qualifier("forumJdbcTemplate")
    private NamedParameterJdbcTemplate forumJdbcTemplate;

    @Override
    public Integer countForumArticle(ForumArticleQueryParams forumArticleQueryParams) {
        String sql = "SELECT count(*) FROM article WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, forumArticleQueryParams);

        Integer total = forumJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<ForumArticle> getForumArticles(ForumArticleQueryParams forumArticleQueryParams) {
        String sql = "SELECT * FROM article WHERE 1=1";

        Map<String, Object> map =new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, forumArticleQueryParams);

        //排序
        sql = sql + " ORDER BY " + forumArticleQueryParams.getOrderBy() + " " + forumArticleQueryParams.getSort();

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", forumArticleQueryParams.getLimit());
        map.put("offset", forumArticleQueryParams.getOffset());

        List<ForumArticle> forumArticleList = forumJdbcTemplate.query(sql, map, new ForumArticleRowmapper());

        return forumArticleList;
    }

    @Override
    public ForumArticle getForumArticleById(Integer forumArticleId) {
        String sql = "SELECT * FROM article WHERE article_id = :articleId";

        Map<String, Object> map = new HashMap<>();
        map.put("articleId", forumArticleId);

        List<ForumArticle> forumArticleList = forumJdbcTemplate.query(sql, map, new ForumArticleRowmapper());

        if (forumArticleList.size() > 0) {
            return forumArticleList.get(0);
        } else {
            return null;
        }
    }

    private String addFilteringSql(String sql, Map<String, Object> map, ForumArticleQueryParams forumArticleQueryParams) {
        if (forumArticleQueryParams.getArticleId() != null) {
            sql = sql + " AND article_id = :articleId";
            map.put("articleId", forumArticleQueryParams.getArticleId());
        }

        if (forumArticleQueryParams.getUserId() != null) {
            sql = sql + " AND user_id = :userId";
            map.put("userId", forumArticleQueryParams.getUserId());
        }

        if (forumArticleQueryParams.getType() != null) {
            sql = sql + " AND type = :type";
            map.put("type", forumArticleQueryParams.getType());
        }

        if(forumArticleQueryParams.getSearch()!=null){
            sql= sql + " AND title LIKE %:search1% AND content LIKE %:search2%";
            map.put("search1",forumArticleQueryParams.getSearch());
            map.put("search2",forumArticleQueryParams.getSearch());
        }
        return sql;
    }
}
