package com.lovepet.animal.dao;

import com.lovepet.animal.dto.UserRegisterRequest;
import com.lovepet.animal.dto.UserUpdateRequest;
import com.lovepet.animal.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer id);

    User getUserByEmail(String email);

    Integer getUserIdByEmail(String email);
    String updateUser(Integer userId, UserUpdateRequest userUpdateRequest);

}


