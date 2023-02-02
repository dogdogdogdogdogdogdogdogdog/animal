package com.lovepet.animal.dao;

import com.lovepet.animal.dto.UserRegisterRequest;
import com.lovepet.animal.model.User;
import org.springframework.http.ResponseEntity;

public interface UserDao {
    public Integer createUser(UserRegisterRequest userRegisterRequest);
    public User getUserById(Integer id);
    public User getUserByEmail(String email);
    public Integer getUserIdByEmail(String email);
}


