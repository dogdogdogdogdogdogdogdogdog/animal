package com.lovepet.animal.controller;


import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.service.PublicAnimalService;
import com.lovepet.animal.util.PublicPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PublicShelter {
    @Autowired
    private PublicAnimalService publicAnimalService;

    @GetMapping("/publicAnimals")
    public ResponseEntity<PublicPage<AnimalData>> getPublicAnimalsData( @RequestParam(defaultValue = "12") Integer limit,
                                                                        @RequestParam(defaultValue = "0") Integer offset,
                                                                        @RequestParam(defaultValue = "1") Integer page,
                                                                        @RequestParam(defaultValue = "所有收容所") String shelterName,
                                                                        @RequestParam(defaultValue = "不分種類") String animalKind){

        AnimalQueryParams animalQueryParams = new AnimalQueryParams();
        animalQueryParams.setLimit(limit);
        animalQueryParams.setOffset(offset);
        animalQueryParams.setPage(page);
        animalQueryParams.setShelter(shelterName);
        animalQueryParams.setKind(animalKind);



       PublicPage<AnimalData> result= publicAnimalService.getPublicAnimals(animalQueryParams);


        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
