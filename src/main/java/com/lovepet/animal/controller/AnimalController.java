package com.lovepet.animal.controller;

import com.lovepet.animal.Test;
import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnimalController {
    @Autowired
   private AnimalService animalService;

//    @GetMapping("/login_register")
//    public String goLoginRegister(){
//        return "login_register";
//    }
//    @GetMapping("/missing")
//    public String goMissing(){
//        return "missing";
//    }
//    @GetMapping("/personal_shelter")
//    public String goPersonalShelter(){
//        return "personal_shelter";
//    }
    @GetMapping("/public_shelter")
    public String goPublicShelter(
            Model model,
            @RequestParam(defaultValue = "12") Integer limit,
            @RequestParam(defaultValue = "0")  Integer offset,
            @RequestParam(defaultValue = "1")  Integer page
            ){

        AnimalQueryParams animalQueryParams=new AnimalQueryParams();
        animalQueryParams.setLimit(limit);
       animalQueryParams.setOffset(offset);
       animalQueryParams.setPage(page);


        System.out.println(animalService.getAnimals(animalQueryParams).size());
        model.addAttribute("pages",animalService.getPages(animalQueryParams));
        model.addAttribute("animals",animalService.getAnimals(animalQueryParams));
        return "public_shelter";
    }
//    @GetMapping("/publish")
//    public String goPublish(){
//        return "publish";
//    }

//    @GetMapping("/animals/{id}")
//    public AnimalData getAnimal(@PathVariable Integer id){
//        System.out.println(id);
//        return animalService.getAnimalById(id);
//    }
}

