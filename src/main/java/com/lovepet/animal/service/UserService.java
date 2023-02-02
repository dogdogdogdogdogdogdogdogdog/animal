package com.lovepet.animal.service;

import com.lovepet.animal.dto.UserLoginRequest;
import com.lovepet.animal.dto.UserRegisterRequest;
import com.lovepet.animal.model.User;


public interface UserService {

    public Integer registerUser(UserRegisterRequest userRegisterRequest);
    public User getUserById(Integer id);
    public User login(UserLoginRequest userLoginRequest);
    public User getUserByEmail(String email);
    public Integer getUserIdByEmail(String email);
}
