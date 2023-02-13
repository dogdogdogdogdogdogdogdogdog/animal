package com.lovepet.animal.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class AnimalMissingRequest {

    @NotNull
    private  Integer userId;

    private  String name;

    @NotNull
    private  String kind;

    private  String variety;

    private  String bodysize;

    private  String sex;

    private  String color;

    private  String age;

    private  String description;

    private  String imageUrl;

    private  String missingArea;

    private MultipartFile animalPhoto;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getBodysize() {
        return bodysize;
    }

    public void setBodysize(String bodysize) {
        this.bodysize = bodysize;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMissingArea() {
        return missingArea;
    }

    public void setMissingArea(String missingArea) {
        this.missingArea = missingArea;
    }

    public MultipartFile getAnimalPhoto() {
        return animalPhoto;
    }

    public void setAnimalPhoto(MultipartFile animalPhoto) {
        this.animalPhoto = animalPhoto;
    }
}
