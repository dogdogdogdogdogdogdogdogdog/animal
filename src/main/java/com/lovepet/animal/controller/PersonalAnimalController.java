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

    @PostMapping("/personalAnimal")//新增個人收容所資料
    public ResponseEntity<PersonalAnimal> createPersonalAnimal(@RequestBody @Valid PersonalAnimalRequest personalAnimalRequest) {
        Integer personalAnimalId = personalAnimalService.createPersonalAnimal(personalAnimalRequest);

        PersonalAnimal personalAnimal = personalAnimalService.getPersonalAnimalById(personalAnimalId);

        return ResponseEntity.status(HttpStatus.CREATED).body(personalAnimal);
    }
    @PutMapping("/personalAnimal/{personalAnimalId}")//修改個人收容所資料
    public  ResponseEntity<PersonalAnimal> updatePersonalAnimal(@PathVariable Integer personalAnimalId,
                                                                @RequestBody @Valid PersonalAnimalRequest personalAnimalRequest) {

        //檢查personalAnimal Id 是否存在
        PersonalAnimal personalAnimal = personalAnimalService.getPersonalAnimalById(personalAnimalId);

        if (personalAnimal == null) {//找不到回傳404 NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改動物的數據
        personalAnimalService.updatePersonalAnimal(personalAnimalId, personalAnimalRequest);

        PersonalAnimal updatedPersonalAnimal = personalAnimalService.getPersonalAnimalById(personalAnimalId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedPersonalAnimal);
    }

    @DeleteMapping("/personalAnimal/{personalAnimalId}")
    public ResponseEntity<?> deletePersonalAnimal(@PathVariable Integer personalAnimalId){
        personalAnimalService.deletePersonalAnimalById(personalAnimalId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }


}
