package com.lovepet.animal.dao.impl;

import com.lovepet.animal.dao.UserDao;
import com.lovepet.animal.dto.UserRegisterRequest;
import com.lovepet.animal.model.User;
import com.lovepet.animal.rowmapper.UserGetIdRowmapper;
import com.lovepet.animal.rowmapper.UserRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserDaoImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate animalJdbcTemplate;


    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql = " insert into user (email,password,name,tel,gender,created_date,last_modified_date)values(:email,:password,:name,:tel,:gender,:create_date,:last_modified_date) ";
        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        map.put("name", userRegisterRequest.getName());
        map.put("tel", userRegisterRequest.getTel());
        map.put("gender",userRegisterRequest.getGender());
        Date now = new Date();
        map.put("create_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        animalJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer id = keyHolder.getKey().intValue();
        return id;


    }

    @Override
    public User getUserById(Integer id) {
        String sql = "SELECT * FROM user WHERE user_id=:userId ";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", id);

        List<User> userList = animalJdbcTemplate.query(sql, map, new UserRowmapper());

        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public void updateUser(Integer userId, UserRegisterRequest userRegisterRequest) {
        String sql = "UPDATE user SET email = :email, password = :password, name = :name, tel = :tel, gender = :gender, last_modified_date = lastModifiedDate " +
                "WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        map.put("name", userRegisterRequest.getName());
        map.put("tel", userRegisterRequest.getTel());
        map.put("gender", userRegisterRequest.getGender());
        map.put("lastModifiedDate", new Date());

        map.put("userId", userId);

        animalJdbcTemplate.update(sql, map);
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "select * from user where email=:email ";
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<User> userList = animalJdbcTemplate.query(sql, map, new UserRowmapper());

        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }


    @Override
    public Integer getUserIdByEmail(String email) {
        String sql = "select user_id from user where email=:email ";
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<User> userList = animalJdbcTemplate.query(sql, map, new UserGetIdRowmapper());

        if (userList.size() > 0) {
            return userList.get(0).getId();
        }
        return null;

    }


}
