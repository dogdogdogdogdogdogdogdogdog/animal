package com.lovepet.animal.controller;

import com.lovepet.animal.dto.AnimalMissingQueryParams;
import com.lovepet.animal.dto.AnimalMissingRequest;
import com.lovepet.animal.model.AnimalMissing;
import com.lovepet.animal.service.AnimalMissingService;
import com.lovepet.animal.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    public ResponseEntity<Page> getAnimalMissing(
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
        Page<AnimalMissing> page = new Page<>();
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

    @PostMapping("/animalsMissing") //新增走失協尋資料
    public ResponseEntity<AnimalMissing> createAnimalMissing(@RequestParam("animalPhoto")MultipartFile animalPhoto,
                                                             @RequestParam("userId") Integer userId,
                                                             @RequestParam("name") String name,
                                                             @RequestParam("kind") String kind,
                                                             @RequestParam("variety") String variety,
                                                             @RequestParam("bodysize") String bodysize,
                                                             @RequestParam("sex") String sex,
                                                             @RequestParam("color") String color,
                                                             @RequestParam("age") String age,
                                                             @RequestParam("description") String description,
                                                             @RequestParam("imageUrl") String imageUrl,
                                                             @RequestParam("missingArea") String missingArea){
        AnimalMissingRequest animalMissingRequest = new AnimalMissingRequest();
        animalMissingRequest.setAnimalPhoto(animalPhoto);
        animalMissingRequest.setUserId(userId);
        animalMissingRequest.setName(name);
        animalMissingRequest.setKind(kind);
        animalMissingRequest.setVariety(variety);
        animalMissingRequest.setBodysize(bodysize);
        animalMissingRequest.setSex(sex);
        animalMissingRequest.setColor(color);
        animalMissingRequest.setAge(age);
        animalMissingRequest.setDescription(description);
        animalMissingRequest.setImageUrl(imageUrl);
        animalMissingRequest.setMissingArea(missingArea);
        Integer animalMissingId = animalMissingService.createAnimalMissing(animalMissingRequest);

        AnimalMissing animalMissing = animalMissingService.getAnimalMissingById(animalMissingId);

        return ResponseEntity.status(HttpStatus.CREATED).body(animalMissing);

    }
    @PutMapping("/animalMissing") //修改走失協尋資料
    public ResponseEntity<AnimalMissing> updatePersonalAnimal(@RequestParam(value = "animalPhoto", required = false) MultipartFile animalPhoto,
                                                              @RequestParam(value = "userId", required = false) Integer userId,
                                                              @RequestParam(value = "name" , required = false) String name,
                                                              @RequestParam(value = "kind", required = false) String kind,
                                                              @RequestParam(value = "variety", required = false) String variety,
                                                              @RequestParam(value = "bodysize", required = false) String bodysize,
                                                              @RequestParam(value = "sex", required = false) String sex,
                                                              @RequestParam(value = "color", required = false) String color,
                                                              @RequestParam(value = "age", required = false) String age,
                                                              @RequestParam(value = "description", required = false) String description,
                                                              @RequestParam(value = "imageUrl", required = false) String imageUrl,
                                                              @RequestParam(value = "missingArea", required = false) String missingArea,
                                                              @RequestParam(value = "item", required = false) Integer animalMissingId) {
        AnimalMissingRequest animalMissingRequest = new AnimalMissingRequest();
        animalMissingRequest.setAnimalPhoto(animalPhoto);
        animalMissingRequest.setUserId(userId);
        animalMissingRequest.setName(name);
        animalMissingRequest.setKind(kind);
        animalMissingRequest.setVariety(variety);
        animalMissingRequest.setBodysize(bodysize);
        animalMissingRequest.setSex(sex);
        animalMissingRequest.setColor(color);
        animalMissingRequest.setAge(age);
        animalMissingRequest.setDescription(description);
        animalMissingRequest.setImageUrl(imageUrl);
        animalMissingRequest.setMissingArea(missingArea);

        //檢查animalMissing Id 是否存在
        AnimalMissing animalMissing = animalMissingService.getAnimalMissingById(animalMissingId);

        if (animalMissing == null) {//找不到回傳404 NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改走失協尋資料

        animalMissingService.updateAnimalMissing(animalMissingId, animalMissingRequest);

        AnimalMissing updateAnimalMissing = animalMissingService.getAnimalMissingById(animalMissingId);

        return ResponseEntity.status(HttpStatus.OK).body(updateAnimalMissing);
    }

    @DeleteMapping("/AnimalsMissing/{animalMissingUserId}/{animalMissingId}")
    public ResponseEntity<?> deleteAnimalMissing(@PathVariable Integer animalMissingUserId,
                                                 @PathVariable Integer animalMissingId){
        animalMissingService.deleteAnimalMissingById(animalMissingUserId, animalMissingId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/getAnimalsMissingComboBox") // 搜尋條件-送資料至下拉選單
    public ResponseEntity getAnimalsMissingComboBox(Model model){
        List list = animalMissingService.getAnimalsMissingComboBox();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
