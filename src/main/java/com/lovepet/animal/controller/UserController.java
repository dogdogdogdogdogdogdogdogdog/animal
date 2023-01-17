package com.lovepet.animal.controller;

import com.lovepet.animal.dto.UserRegisterRequest;

import com.lovepet.animal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/user/register")
    public ResponseEntity goLoginRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        System.out.println(userRegisterRequest.getName());
       Integer id = userService.registerUser(userRegisterRequest);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/users/{email}")
    public ResponseEntity getUserById(@PathVariable Integer id){



        return null;
    }
}
