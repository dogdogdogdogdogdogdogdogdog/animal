package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.UserDao;
import com.lovepet.animal.dto.UserRegisterRequest;
import com.lovepet.animal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;



    @Override
    public Integer registerUser(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
