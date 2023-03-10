package com.lovepet.animal.controller;

import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HtmlController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/animal_food")
    public String goAnimalFood() {
        return "animal_food";
    }

    // 取得頁首
    @GetMapping("/header")
    public String goHeader() {
        return "header";
    }

    // 取得頁腳
    @GetMapping("/footer")
    public String goFooter() {
        return "footer";
    }

    @GetMapping("/user_publish_history")
    public String goUserPublishHistory() {
        return "user_publish_history";
    }

    @GetMapping("/user_management")
    public String goUserManagement() {
        return "user_management";
    }

    @GetMapping("/login_register")
    public String goLoginRegister() {
        return "login_register";
    }

    @GetMapping("/index")
    public String goIndex() {
        return "index";
    }

    @GetMapping("/animal_hospital")
    public String goAnimalHospital() {
        return "animal_hospital";
    }

    @GetMapping("/animal_funeral")
    public String goFuneral() {
        return "animal_funeral";
    }

    @GetMapping("/animal_missing")
    public String goMissing() {
        return "animal_missing";
    }

    @GetMapping("/personal_shelter")
    public String goPersonalShelter() {
        return "personal_shelter";
    }

    @GetMapping("/publish")
    public String goPublish() {
        return "publish";
    }

    @GetMapping("/forum")
    public String goForum(){
        return "forum";
    }

    @GetMapping("/animal_public")
    public String goPublicShelter() {
        return "animal_public";
    }

}

