package com.lovepet.animal.controller;

import com.lovepet.animal.dto.AnimalHospitalQueryParams;
import com.lovepet.animal.model.AnimalHospital;
import com.lovepet.animal.service.AnimalHospitalService;
import com.lovepet.animal.util.PageAnimalHospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalHospitalController {

    @Autowired
    private AnimalHospitalService animalHospitalService;

    @GetMapping("/animal_hospitals")
    public ResponseEntity getAnimalHospital(
            Model model,
            //查詢條件
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String search,
            // 分頁 Pagination
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset
    ){
        AnimalHospitalQueryParams animalHospitalQueryParams = new AnimalHospitalQueryParams();
        animalHospitalQueryParams.setArea(area);
        animalHospitalQueryParams.setSearch(search);
        animalHospitalQueryParams.setLimit(limit);
        animalHospitalQueryParams.setOffset(offset);

        // 取得 animalHospital list
        List<AnimalHospital> animalHospitalList = animalHospitalService.getAnimalHospitals(animalHospitalQueryParams);

        // 取得 animalHospital 總數
        Integer total = animalHospitalService.countAnimalHospital(animalHospitalQueryParams);

        // 分頁
        PageAnimalHospital<AnimalHospital> page = new PageAnimalHospital();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(animalHospitalList);

        return ResponseEntity.status(HttpStatus.OK).body(page);

    }

    @GetMapping("/getAnimalHospitalsComboBox")
    // 搜尋條件-送資料至下拉選單
    public ResponseEntity getAnimalHospitalComboBox(Model model){
        List list = animalHospitalService.getAnimalHospitalsComboBox();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
