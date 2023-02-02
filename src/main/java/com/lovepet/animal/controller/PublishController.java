package com.lovepet.animal.controller;

import com.lovepet.animal.dto.PublishAnimalRequest;

import com.lovepet.animal.model.PublishData;
import com.lovepet.animal.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class PublishController {

    @Autowired
    private PublishService publishService;

    @GetMapping("/publish_animals/{userId}")
    public ResponseEntity<List<PublishData>> getPublishData(@PathVariable String userId) {
        List<PublishData> publishData;
        if (userId.equals("all")) {
            publishData = publishService.getPublishById(null);
        } else {
            publishData = publishService.getPublishById(Integer.parseInt(userId));
        }

        if (publishData != null) {
            return ResponseEntity.status(HttpStatus.OK).body(publishData);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


    }


    @PostMapping("/publish_animal/textData")
    public ResponseEntity publish(@RequestParam("animalPhoto") MultipartFile animalPhoto,
                                  @RequestParam("kind") String kind,
                                  @RequestParam("variety") String variety,
                                  @RequestParam("sex") String sex,
                                  @RequestParam("body") String body,
                                  @RequestParam("color") String color,
                                  @RequestParam("age") String age,
                                  @RequestParam("ligation") String ligation,
                                  @RequestParam("address") String address,
                                  @RequestParam("remark") String remark,
                                  HttpSession session) {
        PublishAnimalRequest publishAnimalRequest = new PublishAnimalRequest();
        publishAnimalRequest.setAnimalPhoto(animalPhoto);
        publishAnimalRequest.setKind(kind);
        publishAnimalRequest.setVariety(variety);
        publishAnimalRequest.setSex(sex);
        publishAnimalRequest.setBodyShape(body);
        publishAnimalRequest.setColor(color);
        publishAnimalRequest.setAge(age);
        publishAnimalRequest.setLigation(ligation);
        publishAnimalRequest.setAddress(address);
        publishAnimalRequest.setRemark(remark);
        publishAnimalRequest.setId((Integer) session.getAttribute("userId"));

        publishService.createPublish(publishAnimalRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/publish_animals")
    public ResponseEntity updatePublish(@RequestParam(value = "userId", required = false) Integer userId,
                                        @RequestParam(value = "animalPhoto", required = false) MultipartFile animalPhoto,
                                        @RequestParam(value = "updateKind", required = false) String kind,
                                        @RequestParam(value = "updateVariety", required = false) String variety,
                                        @RequestParam(value = "updateSex", required = false) String sex,
                                        @RequestParam(value = "updateBodyShape", required = false) String body,
                                        @RequestParam(value = "updateColor", required = false) String color,
                                        @RequestParam(value = "updateAge", required = false) String age,
                                        @RequestParam(value = "updateLigation", required = false) String ligation,
                                        @RequestParam(value = "updateAddress", required = false) String address,
                                        @RequestParam(value = "updateRemark", required = false) String remark,
                                        @RequestParam(value = "item", required = true) Integer item
    ) {

        PublishAnimalRequest publishAnimalRequest = new PublishAnimalRequest();
        publishAnimalRequest.setAnimalPhoto(animalPhoto);
        publishAnimalRequest.setKind(kind);
        publishAnimalRequest.setVariety(variety);
        publishAnimalRequest.setSex(sex);
        publishAnimalRequest.setBodyShape(body);
        publishAnimalRequest.setColor(color);
        publishAnimalRequest.setAge(age);
        publishAnimalRequest.setLigation(ligation);
        publishAnimalRequest.setAddress(address);
        publishAnimalRequest.setRemark(remark);
        publishAnimalRequest.setId(userId);
        publishAnimalRequest.setItem(item);
        publishService.updatePublish(publishAnimalRequest);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/publish_animals/{userId}/{item}")
    public ResponseEntity delPublish(@PathVariable(required = true) Integer userId, @PathVariable(required = true) Integer item) {
        publishService.delPublish(userId, item);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
