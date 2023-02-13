package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.MessageDao;
import com.lovepet.animal.dto.MessageQueryParams;
import com.lovepet.animal.model.UserFeedback;
import com.lovepet.animal.rowmapper.UserFeedbackRowmapper;
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
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createMessage(MessageQueryParams messageQueryParams) {
        String sql=" insert into user_feedback (t_id,user_id,message,create_date) values(:tId,:userId,:message,:createDate) ";
        Map<String,Object> map=new HashMap<>();
        map.put("tId",messageQueryParams.gettId());
        map.put("userId",messageQueryParams.getUserId());
        map.put("message",messageQueryParams.getMessage());
        map.put("createDate",new Date());

        namedParameterJdbcTemplate.update(sql,map);



    }

    @Override
    public List<UserFeedback> getMessage(MessageQueryParams messageQueryParams) {

        String sql="select email,message,create_date from user LEFT JOIN user_feedback uf on user.user_id = uf.user_id where t_id=:tId";
        Map<String,Object> map=new HashMap<>();
        System.out.println(messageQueryParams.gettId());
        map.put("tId",messageQueryParams.gettId());

     List<UserFeedback> userFeedbacks =   namedParameterJdbcTemplate.query(sql,map,new UserFeedbackRowmapper());

      if(userFeedbacks.size()>0){
          return userFeedbacks;
      }

        return null;



    }
}
