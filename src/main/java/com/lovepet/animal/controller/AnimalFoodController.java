package com.lovepet.animal.controller;

import com.lovepet.animal.dto.AnimalFoodQueryParams;
import com.lovepet.animal.model.AnimalFood;
import com.lovepet.animal.service.AnimalFoodService;
import com.lovepet.animal.util.PageAnimalFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;

@Controller
public class AnimalFoodController {

    @Autowired
    private AnimalFoodService animalFoodService;

    @GetMapping("/animal_food")
    public String goAnimalFood(
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
        PageAnimalFood<AnimalFood> page = new PageAnimalFood<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(animalFoodList);

        // 搜尋條件-送資料至下拉選單
        HashSet<String> hashSetPlace = new HashSet<>();
        HashSet<String> hashSetCategory = new HashSet<>();

        for (int i = 0; i < animalFoodList.size(); i++) {
            String placeStr = animalFoodList.get(i).getProductionPlace();
            String categoryStr = animalFoodList.get(i).getCategory();
            hashSetPlace.add(placeStr);
            hashSetCategory.add(categoryStr);
        }

        model.addAttribute("hashset_place", hashSetPlace);
        model.addAttribute("hashset_category", hashSetCategory);
        model.addAttribute("animal_food_list", animalFoodList);
//        model.addAttribute("", );
//        model.addAttribute("placeSet", animalFoodService.getProductionPlaceSet(animalFoodQueryParams));
//        model.addAttribute("categorySet", animalFoodService.getCategorySet(animalFoodQueryParams));
        return "animal_food";
    }

}
