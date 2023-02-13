package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.MessageDao;
import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.model.Message;
import com.lovepet.animal.rowmapper.MessageRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createMessage(MessageQueryParams messageQueryParams) {
        String sql = " INSERT INTO feedback (article_id, user_id, content, post_date, modified_date) values(:articleId, :userId, :content, :postDate, :modifiedDate) ";
        Map<String, Object> map = new HashMap<>();
        map.put("articleId", messageQueryParams.getArticleId());
        map.put("userId", messageQueryParams.getUserId());
        map.put("content", messageQueryParams.getContent());
        map.put("postDate", new Date());
        map.put("modifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Integer countMessage(MessageQueryParams messageQueryParams) {
        String sql = "SELECT count(*) FROM feedback WHERE article_id = :articleId";

        Map<String, Object> map = new HashMap<>();
        map.put("articleId", messageQueryParams.getArticleId());

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Message> getMessage(MessageQueryParams messageQueryParams) {
        String sql = " SELECT * FROM feedback WHERE article_id=:articleId ORDER BY post_date ASC ";
//        String sql="select email,message,create_date from user LEFT JOIN user_feedback uf on user.user_id = uf.user_id where t_id=:tId";
        Map<String, Object> map = new HashMap<>();
        System.out.println(messageQueryParams.getArticleId());
        map.put("articleId", messageQueryParams.getArticleId());

        List<Message> messages = namedParameterJdbcTemplate.query(sql, map, new MessageRowmapper());

        if (messages.size() > 0) {
            return messages;
        }

        return null;


    }
}
