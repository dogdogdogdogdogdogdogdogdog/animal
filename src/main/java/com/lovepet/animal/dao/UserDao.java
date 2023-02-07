package com.lovepet.animal.dao;

import com.lovepet.animal.dto.UserRegisterRequest;
import com.lovepet.animal.model.User;
import org.springframework.http.ResponseEntity;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer id);

     User getUserByEmail(String email);

    Integer getUserIdByEmail(String email);
}


