package com.lovepet.animal.controller;


import com.lovepet.animal.dto.MissingAnimalRequest;
import com.lovepet.animal.dto.MissingAnimalsQueryParams;
import com.lovepet.animal.model.MissingData;
import com.lovepet.animal.service.MissingService;
import com.lovepet.animal.util.MissingPage;
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
public class MissingController {

    @Autowired
    private MissingService missingService;


    @GetMapping("/missingAnimals")
    public ResponseEntity<MissingPage<MissingData>> getMissingAnimals(
            //查詢條件
            @RequestParam(required = false) String kind,
            @RequestParam(required = false) String sex,
            //排序
            @RequestParam(defaultValue = "missing_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            //分頁
            @RequestParam(defaultValue = "2") @Max(1000) @Min(0) Integer limit,//一次查詢幾筆
            @RequestParam(defaultValue = "0") @Min(0) Integer offset//跳過幾筆資料
    ) {
        System.out.println(offset);
        MissingAnimalsQueryParams missingAnimalsQueryParams=new MissingAnimalsQueryParams();
        missingAnimalsQueryParams.setKind(kind);
        missingAnimalsQueryParams.setSex(sex);
        missingAnimalsQueryParams.setOrderBy(orderBy);
        missingAnimalsQueryParams.setSort(sort);
        missingAnimalsQueryParams.setLimit(limit);
        missingAnimalsQueryParams.setOffset(offset);

        MissingPage missingPage =new MissingPage();

        List<MissingData> missingDatas =  missingService.getMissingAnimals(missingAnimalsQueryParams);
        Integer total =missingService.countMissingAnimals(missingAnimalsQueryParams);

        missingPage.setMissingAnimals(missingDatas);
        missingPage.setLimit(limit);
        missingPage.setOffset(offset);
        missingPage.setTotal(total);

        return ResponseEntity.status(HttpStatus.OK).body(missingPage);
    }



    //取得所有使用者刊登的所有協尋資料
    @GetMapping("/missingAnimals/{userId}")
    public ResponseEntity<List<MissingData>> getUserMissingAnimals(@PathVariable Integer userId) {
        System.out.println(userId);
        List<MissingData> missingDatas = missingService.getMissingById(userId);
        if (missingDatas != null) {
            return ResponseEntity.status(HttpStatus.OK).body(missingDatas);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




    //新增走失資料
    @PostMapping("/missingAnimals")
    public ResponseEntity publishMissing(@RequestParam("missingAnimalPhoto") MultipartFile animalPhoto,
                                         @RequestParam("missingKind") String kind,
                                         @RequestParam("missingVariety") String variety,
                                         @RequestParam("missingSex") String sex,
                                         @RequestParam("missingBody") String body,
                                         @RequestParam("missingColor") String color,
                                         @RequestParam("missingAge") String age,
                                         @RequestParam("missingDate") String date,
                                         @RequestParam("missingPlace") String place,
                                         @RequestParam("missingRemark") String remark,
                                         @RequestParam("userId") Integer userId,
                                        @RequestParam("photoUrl") String photoUrl) {

        MissingAnimalRequest missingAnimalRequest = new MissingAnimalRequest();
        missingAnimalRequest.setMissingAnimalPhoto(animalPhoto);
        missingAnimalRequest.setMissingKind(kind);
        missingAnimalRequest.setMissingVariety(variety);
        missingAnimalRequest.setMissingSex(sex);
        missingAnimalRequest.setMissingBodyShape(body);
        missingAnimalRequest.setMissingColor(color);
        missingAnimalRequest.setMissingAge(age);
        missingAnimalRequest.setMissingDate(date);
        missingAnimalRequest.setMissingPlace(place);
        missingAnimalRequest.setMissingRemark(remark);
        missingAnimalRequest.setUserId(userId);
        missingAnimalRequest.setPhotoUrl(photoUrl);
        missingService.createMissing(missingAnimalRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
