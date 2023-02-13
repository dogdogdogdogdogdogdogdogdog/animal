package com.lovepet.animal.controller;

import com.lovepet.animal.dto.AnimalFoodQueryParams;
import com.lovepet.animal.model.AnimalFood;
import com.lovepet.animal.service.AnimalFoodService;
import com.lovepet.animal.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AnimalFoodController {

    @Autowired
    private AnimalFoodService animalFoodService;

    @GetMapping("/animal_foods")
    public ResponseEntity getAnimalFood(
            Model model,
            // 查詢條件 Filtering
            @RequestParam(required = false) String place,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search,
            // 分頁 Pagination
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset
    ) {

        AnimalFoodQueryParams animalFoodQueryParams = new AnimalFoodQueryParams();
        animalFoodQueryParams.setProductionPlace(place);
        animalFoodQueryParams.setCategory(category);
        animalFoodQueryParams.setSearch(search);
        animalFoodQueryParams.setLimit(limit);
        animalFoodQueryParams.setOffset(offset);

        // 取得 AnimalFood list
        List<AnimalFood> animalFoodList = animalFoodService.getAnimalFoods(animalFoodQueryParams);

        // 取得 AnimalFood 總數
        Integer total = animalFoodService.countAnimalFood(animalFoodQueryParams);

        // 分頁
        Page<AnimalFood> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(animalFoodList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

}
