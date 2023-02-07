package com.lovepet.animal.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class PersonalAnimalRequest {

    @NotNull
    private Integer userId;
    @NotNull
    private String animalName;

    @NotNull
    private String animalKind;

    @NotNull
    private String animalVariety;

    @NotNull
    private String animalSex;

    @NotNull
    private String animalAge;

    @NotNull
    private String animalBodysize;

    private String animalColor;

    @NotNull
    private String animalSterilization;

    @NotNull
    private String animalBacterin;

    private MultipartFile animalPhoto;

    private String imageUrl;

    private String area;

    private String description;

    public MultipartFile getAnimalPhoto() {
        return animalPhoto;
    }

    public void setAnimalPhoto(MultipartFile animalPhoto) {
        this.animalPhoto = animalPhoto;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalKind() {
        return animalKind;
    }

    public void setAnimalKind(String animalKind) {
        this.animalKind = animalKind;
    }

    public String getAnimalVariety() {
        return animalVariety;
    }

    public void setAnimalVariety(String animalVariety) {
        this.animalVariety = animalVariety;
    }

    public String getAnimalSex() {
        return animalSex;
    }

    public void setAnimalSex(String animalSex) {
        this.animalSex = animalSex;
    }

    public String getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(String animalAge) {
        this.animalAge = animalAge;
    }

    public String getAnimalBodysize() {
        return animalBodysize;
    }

    public void setAnimalBodysize(String animalBodysize) {
        this.animalBodysize = animalBodysize;
    }

    public String getAnimalColor() {
        return animalColor;
    }

    public void setAnimalColor(String animalColor) {
        this.animalColor = animalColor;
    }

    public String getAnimalSterilization() {
        return animalSterilization;
    }

    public void setAnimalSterilization(String animalSterilization) {
        this.animalSterilization = animalSterilization;
    }

    public String getAnimalBacterin() {
        return animalBacterin;
    }

    public void setAnimalBacterin(String animalBacterin) {
        this.animalBacterin = animalBacterin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
