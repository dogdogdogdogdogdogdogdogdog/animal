package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.ForumArticleDao;
import com.lovepet.animal.dto.ForumArticleQueryParams;
import com.lovepet.animal.dto.ForumArticleRequest;
import com.lovepet.animal.dto.PersonalAnimalQueryParams;
import com.lovepet.animal.model.ForumArticle;
import com.lovepet.animal.rowmapper.ForumArticleRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
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
            sql= sql + " AND title LIKE :search1 OR content LIKE :search2";
            map.put("search1", "%"+forumArticleQueryParams.getSearch()+"%");
            map.put("search2", "%"+forumArticleQueryParams.getSearch()+"%");
        }
        return sql;
    }

    @Override
    public Integer createForumArticle(ForumArticleRequest forumArticleRequest) {
        String sql = "INSERT INTO article(user_id, type, title, content, views, likes, unlikes, post_date, modified_date) " +
                "VALUES (:userId, :type, :title, :content, 0, 0, 0, :postDate, :modifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", forumArticleRequest.getUserId());
        map.put("type", forumArticleRequest.getType());
        map.put("title", forumArticleRequest.getTitle());
        map.put("content", forumArticleRequest.getContent());

        Date now = new Date();
        map.put("postDate", now);
        map.put("modifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        forumJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);

        int forumArticleId = keyHolder.getKey().intValue();

        return forumArticleId;
    }

    @Override
    public void updateForumArticle(Integer forumArticleId, ForumArticleRequest forumArticleRequest) {
        String sql = "UPDATE article SET type = :type, title = :title, content = :content, modified_date = :modifiedDate " +
                "WHERE article_id = :articleId";

        Map<String, Object> map = new HashMap<>();
        map.put("type", forumArticleRequest.getType());
        map.put("title", forumArticleRequest.getTitle());
        map.put("content", forumArticleRequest.getContent());
        map.put("modifiedDate", new Date());

        map.put("articleId", forumArticleId);

        forumJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteForumArticleById(Integer forumArticleUserId, Integer forumArticleId) {
        String sql = "DELETE FROM article WHERE user_id = :userId AND article_id = :articleId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", forumArticleUserId);
        map.put("articleId", forumArticleId);
        forumJdbcTemplate.update(sql, map);

    }
}
