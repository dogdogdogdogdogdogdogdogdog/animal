package com.lovepet.animal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnimalController {

    @GetMapping("/login_register")
    public String goLoginRegister(){
        return "login_register";
    }
    @GetMapping("/missing")
    public String goMissing(){
        return "missing";
    }
    @GetMapping("/personal_shelter")
    public String goPersonalShelter(){
        return "personal_shelter";
    }
    @GetMapping("/public_shelter")
    public String goPublicShelter(){
        return "public_shelter";
    }
    @GetMapping("/publish")
    public String goPublish(){
        return "publish";
    }
}
