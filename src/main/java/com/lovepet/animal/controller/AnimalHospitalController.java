package com.lovepet.animal.controller;

import com.lovepet.animal.dto.AnimalHospitalQueryParams;
import com.lovepet.animal.model.AnimalHospital;
import com.lovepet.animal.service.AnimalHospitalService;
import com.lovepet.animal.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalHospitalController {

    @Autowired
    private AnimalHospitalService animalHospitalService;

    @GetMapping("/animal_hospitals")
    public ResponseEntity<Page<AnimalHospital>> getAnimalHospitals(
            Model model,
            //查詢條件
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String search,
            // 分頁 Pagination
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset
    ) {
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
        Page<AnimalHospital> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(animalHospitalList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/animal_hospital/{hospitalId}")
    public ResponseEntity<AnimalHospital> getAnimalHospital(@PathVariable Integer hospitalId) {
        AnimalHospital animalHospital = animalHospitalService.getAnimalHospitalById(hospitalId);

        if (animalHospital != null) {
            return ResponseEntity.status(HttpStatus.OK).body(animalHospital);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
