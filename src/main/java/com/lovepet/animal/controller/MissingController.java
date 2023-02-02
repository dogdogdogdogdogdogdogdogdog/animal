package com.lovepet.animal.controller;


import com.lovepet.animal.dto.MissingAnimalRequest;
import com.lovepet.animal.model.MissingData;
import com.lovepet.animal.service.MissingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class MissingController {

    @Autowired
    MissingService missingService;

    @GetMapping("/missing_animals/{userId}")
    public ResponseEntity<List<MissingData>> getPublishData(@PathVariable Integer userId) {

        List<MissingData> missingData = missingService.getMissingById(userId);


        if (missingData != null) {
            return ResponseEntity.status(HttpStatus.OK).body(missingData);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


    }

    @PostMapping("missing_animal/textData")
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
                                         HttpSession session) {

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
        missingAnimalRequest.setMissingId((Integer) session.getAttribute("userId"));
        missingService.createMissing(missingAnimalRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
