package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.UserDao;
import com.lovepet.animal.dto.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql =" insert into user (email,password,name,tel,created_date,last_modified_date)values(:email,:password,:name,:tel,:create_date,:last_modified_date) ";
        Map<String,Object> map=new HashMap<>();
        map.put("email",userRegisterRequest.getEmail());
        map.put("password",userRegisterRequest.getPassword());
        map.put("name",userRegisterRequest.getName());
        map.put("tel",userRegisterRequest.getTel());
       Date now = new Date();
        map.put("create_date",now);
        map.put("last_modified_date",now);

        KeyHolder keyHolder=new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

       Integer id= keyHolder.getKey().intValue();
        return id;



    }
}
