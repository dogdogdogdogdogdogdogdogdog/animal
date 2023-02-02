package com.lovepet.animal.controller;

import com.lovepet.animal.dto.PersonalAnimalQueryParams;
import com.lovepet.animal.dto.PersonalAnimalRequest;
import com.lovepet.animal.model.PersonalAnimal;
import com.lovepet.animal.service.PersonalAnimalService;
import com.lovepet.animal.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class PersonalAnimalController {

    @Autowired
    private PersonalAnimalService personalAnimalService;

    @GetMapping("/personalAnimals")//查詢個人收容所資料(全部)
    public ResponseEntity<Page<PersonalAnimal>> getPersonalAnimals(
            //查詢條件
            @RequestParam(required = false) String kind,
            @RequestParam(required = false) String sex,

            //排序
            @RequestParam(defaultValue = "last_modified_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            //分頁
            @RequestParam(defaultValue = "12") @Max(1000) @Min(0) Integer limit,//一次查詢幾筆
            @RequestParam(defaultValue = "0") @Min(0) Integer offset//跳過幾筆資料
    ) {
        PersonalAnimalQueryParams personalAnimalQueryParams = new PersonalAnimalQueryParams();
        personalAnimalQueryParams.setKind(kind);
        personalAnimalQueryParams.setSex(sex);
        personalAnimalQueryParams.setOrderBy(orderBy);
        personalAnimalQueryParams.setSort(sort);
        personalAnimalQueryParams.setLimit(limit);
        personalAnimalQueryParams.setOffset(offset);

        //取得list
        List<PersonalAnimal> personalAnimalList = personalAnimalService.getPersonalAnimals(personalAnimalQueryParams);

        //取的總筆數
        Integer total = personalAnimalService.countPersonalAnimal(personalAnimalQueryParams);

        //分頁
        Page<PersonalAnimal> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(personalAnimalList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/personalAnimals/{personalAnimalId}")//查詢個人收容所資料(單筆)
    public ResponseEntity<PersonalAnimal> getPersonalAnimal(@PathVariable Integer personalAnimalId) {
        PersonalAnimal personalAnimal = personalAnimalService.getPersonalAnimalById(personalAnimalId);

        if (personalAnimal != null) {
            return ResponseEntity.status(HttpStatus.OK).body(personalAnimal);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/personalAnimals")//新增個人收容所資料
    public ResponseEntity<PersonalAnimal> createPersonalAnimal(@RequestBody @Valid PersonalAnimalRequest personalAnimalRequest) {
        Integer personalAnimalId = personalAnimalService.createPersonalAnimal(personalAnimalRequest);

        PersonalAnimal personalAnimal = personalAnimalService.getPersonalAnimalById(personalAnimalId);

        return ResponseEntity.status(HttpStatus.CREATED).body(personalAnimal);
    }

    @PutMapping("/personalAnimals/{personalAnimalId}")//修改個人收容所資料
    public ResponseEntity<PersonalAnimal> updatePersonalAnimal(@PathVariable Integer personalAnimalId,
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

    @DeleteMapping("/personalAnimals/{personalAnimalId}")
    public ResponseEntity<?> deletePersonalAnimal(@PathVariable Integer personalAnimalId) {
        personalAnimalService.deletePersonalAnimalById(personalAnimalId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
