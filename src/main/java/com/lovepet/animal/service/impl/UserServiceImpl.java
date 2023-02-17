package com.lovepet.animal.service.impl;

import com.lovepet.animal.dao.UserDao;
import com.lovepet.animal.dto.UserLoginRequest;
import com.lovepet.animal.dto.UserRegisterRequest;
import com.lovepet.animal.dto.UserUpdateRequest;
import com.lovepet.animal.model.User;
import com.lovepet.animal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public Integer registerUser(UserRegisterRequest userRegisterRequest) {

        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());


        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);
        return userDao.createUser(userRegisterRequest);
    }


    @Override
    public User getUserById(Integer id) {

        return userDao.getUserById(id);
    }


    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());
        if (user == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            return null;
        }
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());


        if (user.getPassword().equals(hashedPassword)) {
            return user;
        }

//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return null;
    }

    @Override
    public String updateUser(Integer userId, UserUpdateRequest userUpdateRequest) {
        User user = userDao.getUserById(userId);
        if (user == null) {
            System.out.println("null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String hashedPassword = DigestUtils.md5DigestAsHex(userUpdateRequest.getPassword().getBytes());
        System.out.println(hashedPassword);
        System.out.println(user.getPassword());
        if (user.getPassword().equals(hashedPassword)) {
            System.out.println("密碼正確");
            userUpdateRequest.setPassword(hashedPassword);

            return userDao.updateUser(userId, userUpdateRequest);
        }else {
            System.out.println("密碼錯誤");
            return "密碼錯誤";
        }

    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public Integer getUserIdByEmail(String email) {

        return userDao.getUserIdByEmail(email);

    }


}
