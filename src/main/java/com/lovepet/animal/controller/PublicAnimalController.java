package com.lovepet.animal.controller;


import com.lovepet.animal.dto.AnimalQueryParams;
import com.lovepet.animal.model.AnimalData;
import com.lovepet.animal.service.AnimalService;
import com.lovepet.animal.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@RestController
public class PublicAnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping("/publicAnimals")//查詢個人收容所資料(全部)
    public ResponseEntity<Page<AnimalData>> getPersonalAnimals(
            //查詢條件
            @RequestParam(required = false) String kind,
            @RequestParam(required = false) String area,

            //排序
            @RequestParam(defaultValue = "animal_opendate") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            //分頁
            @RequestParam(defaultValue = "12") @Max(1000) @Min(0) Integer limit,//一次查詢幾筆
            @RequestParam(defaultValue = "0") @Min(0) Integer offset,//跳過幾筆資料
            //查單筆
            @RequestParam(required = false) Integer id


    ) {

        AnimalQueryParams animalQueryParams = new AnimalQueryParams();
        animalQueryParams.setOffset(offset);
        animalQueryParams.setLimit(limit);
        animalQueryParams.setKind(kind);
        animalQueryParams.setShelter(area);
        animalQueryParams.setId(id);
        Page page = animalService.getAnimals(animalQueryParams);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


}
