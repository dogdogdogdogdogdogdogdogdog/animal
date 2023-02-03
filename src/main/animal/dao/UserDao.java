package com.lovepet.animal.dao;

import com.lovepet.animal.dto.UserRegisterRequest;

public interface UserDao {
    public Integer createUser(UserRegisterRequest userRegisterRequest);
}
