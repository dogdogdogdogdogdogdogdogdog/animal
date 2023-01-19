package com.lovepet.animal.controller;

import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.service.PersonalAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PersonalAnimalController {

    @Autowired
    private PersonalAnimalService personalAnimalService;

    @GetMapping("/personalAnimal/{personalAnimalId}")//查詢個人收容所資料
    public ResponseEntity<PersonalAnimal> getPersonalAnimal(@PathVariable Integer personalAnimalId) {
        PersonalAnimal personalAnimal = personalAnimalService.getPersonalAnimalById(personalAnimalId);

        if (personalAnimal != null) {
            return ResponseEntity.status(HttpStatus.OK).body(personalAnimal);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/personalAnimal")
    public ResponseEntity<PersonalAnimal> createPersonalAnimal(@RequestBody @Valid PersonalAnimalRequest personalAnimalRequest) {
        Integer personalAnimalId = personalAnimalService.createPersonalAnimal(personalAnimalRequest);

        PersonalAnimal personalAnimal = personalAnimalService.getPersonalAnimalById(personalAnimalId);

        return ResponseEntity.status(HttpStatus.CREATED).body(personalAnimal);
    }
}
