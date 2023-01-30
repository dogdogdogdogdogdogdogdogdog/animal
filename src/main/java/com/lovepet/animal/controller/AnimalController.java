package com.lovepet.animal.controller;

import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping("/login_register")
    public String goLoginRegister(){
        return "login_register";
    }

    @GetMapping("/missing")
    public String goMissing() {
        return "missing";
    }

    @GetMapping("/personal_shelter")
    public String goPersonalShelter() {
        return "personal_shelter";
    }

    @GetMapping("/publish")
    public String goPublish() {
        return "publish";
    }


    @GetMapping("/public_shelter")
    public String goPublicShelter(
            Model model,
            @RequestParam(defaultValue = "12") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "所有收容所") String shelterName,
            @RequestParam(defaultValue = "不分種類") String animalKind
    ) {

        AnimalQueryParams animalQueryParams = new AnimalQueryParams();
        animalQueryParams.setLimit(limit);
        animalQueryParams.setOffset(offset);
        animalQueryParams.setPage(page);
        animalQueryParams.setShelter(shelterName);
        animalQueryParams.setKind(animalKind);

        System.out.println(animalService.getAnimals(animalQueryParams).getPages().size());
        model.addAttribute("shelters", animalService.getShelter());
        model.addAttribute("pages", animalService.getAnimals(animalQueryParams).getPages());
        model.addAttribute("animals", animalService.getAnimals(animalQueryParams).getAnimals());
        return "public_shelter";
    }

}

