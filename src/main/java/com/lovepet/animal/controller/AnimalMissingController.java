package com.lovepet.animal.controller;

import com.lovepet.animal.dto.AnimalMissingQueryParams;
import com.lovepet.animal.model.AnimalMissing;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.service.AnimalMissingService;
import com.lovepet.animal.util.Page;
import com.lovepet.animal.util.PageAnimalMissing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class AnimalMissingController {

    @Autowired
    private AnimalMissingService animalMissingService;

    @GetMapping("/animalsMissing") //查詢走失協尋資料(全部)
    public ResponseEntity<PageAnimalMissing> getAnimalMissing(
            //查詢條件
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String kind,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) String missing_area,

            //排序
            @RequestParam(defaultValue = "created_data") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            //分頁
            @RequestParam(defaultValue = "12") @Max(1000) @Min(0) Integer limit,//一次查詢幾筆
            @RequestParam(defaultValue = "0") @Min(0) Integer offset//跳過幾筆資料

    ) {
        AnimalMissingQueryParams animalMissingQueryParams = new AnimalMissingQueryParams();
        animalMissingQueryParams.setKind(kind);
        animalMissingQueryParams.setSex(sex);
        animalMissingQueryParams.setMissingArea(missing_area);
        animalMissingQueryParams.setOrderBy(orderBy);
        animalMissingQueryParams.setSort(sort);
        animalMissingQueryParams.setLimit(limit);
        animalMissingQueryParams.setOffset(offset);
        animalMissingQueryParams.setId(id);

        //取得list
        List<AnimalMissing> animalMissingList = animalMissingService.getAnimalsMissing(animalMissingQueryParams);

        //取的總筆數
        Integer total = animalMissingService.countAnimalMissing(animalMissingQueryParams);

        //分頁
        Page<PersonalAnimal> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);

        page.setResults(animalMissingList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/animalsMissing/{animalMissingId}")
    public ResponseEntity<AnimalMissing> getAnimalMissing(@PathVariable Integer animalMissingId){
        AnimalMissing animalMissing = animalMissingService.getAnimalMissingById(animalMissingId);

        if(animalMissing != null){
            return ResponseEntity.status(HttpStatus.OK).body(animalMissing);

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/animalsMissing")
    public ResponseEntity<AnimalMissing> createAnimalMissing(@RequestParam()MultipartFile )



}
