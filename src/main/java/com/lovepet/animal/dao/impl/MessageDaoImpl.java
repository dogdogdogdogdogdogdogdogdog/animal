package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.MessageDao;
import com.lovepet.animal.dto.ForumArticleQueryParams;
import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.model.UserFeedback;
import com.lovepet.animal.rowmapper.UserFeedbackRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageDaoImpl implements MessageDao {

    @Autowired
    @Qualifier("forumJdbcTemplate")
    private NamedParameterJdbcTemplate forumJdbcTemplate;

    @Override
    public void createMessage(MessageQueryParams messageQueryParams) {
        String sql=" insert into feedback (article_id,user_id,content,post_date) values(:articleId,:userId,:content,:postDate) ";
        Map<String,Object> map=new HashMap<>();
        map.put("articleId",messageQueryParams.getArticleId());
        map.put("userId",messageQueryParams.getUserId());
        map.put("content",messageQueryParams.getContent());
        map.put("postDate",new Date());

        forumJdbcTemplate.update(sql,map);
    }

    @Override
    public Integer countMessage(MessageQueryParams messageQueryParams) {
        String sql = "SELECT count(*) FROM feedback WHERE article_id=:articleId";

        Map<String, Object> map = new HashMap<>();
        map.put("articleId", messageQueryParams.getArticleId());

        Integer total = forumJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<UserFeedback> getMessage(MessageQueryParams messageQueryParams) {
        String sql=" select * from feedback where article_id=:articleId order by floor ASC ";
//        String sql="select email,message,create_date from user LEFT JOIN user_feedback uf on user.user_id = uf.user_id where t_id=:tId";
        Map<String,Object> map=new HashMap<>();
        System.out.println(messageQueryParams.getArticleId());
        map.put("articleId",messageQueryParams.getArticleId());

     List<UserFeedback> userFeedbacks =   forumJdbcTemplate.query(sql,map,new UserFeedbackRowmapper());

      if(userFeedbacks.size()>0){
          return userFeedbacks;
      }

        return null;



    }
}
