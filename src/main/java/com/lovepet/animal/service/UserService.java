package com.lovepet.animal.service;

import com.lovepet.animal.dto.UserLoginRequest;
import com.lovepet.animal.dto.UserRegisterRequest;
import com.lovepet.animal.dto.UserUpdateRequest;
import com.lovepet.animal.model.User;


public interface UserService {

    Integer registerUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer id);

    User login(UserLoginRequest userLoginRequest);

    User getUserByEmail(String email);

    Integer getUserIdByEmail(String email);

    String updateUser(Integer userId, UserUpdateRequest userUpdateRequest);
}
