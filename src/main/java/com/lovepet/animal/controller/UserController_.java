package com.lovepet.animal.controller;


import com.lovepet.animal.dto.UserRegisterRequest;
import com.lovepet.animal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController_ {

    @Autowired
    private UserService userService;
   @GetMapping ("/login_register")
    public String add(Model model) {
      UserRegisterRequest userRegisterRequest=new UserRegisterRequest();
        model.addAttribute("user",userRegisterRequest);
        return "login_register";
    }
//    @PostMapping("/add")
//    public String add(@ModelAttribute @Valid UserRegisterRequest userRegisterRequest, Model model) {
//
//        System.out.println(userRegisterRequest.getName());
//            model.addAttribute("user", userRegisterRequest);
//            return "add";
//
//    }
}
