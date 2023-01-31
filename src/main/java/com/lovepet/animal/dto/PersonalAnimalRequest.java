package com.lovepet.animal.dto;

import javax.validation.constraints.NotNull;

public class PersonalAnimalRequest {

    @NotNull
    private String animalName;

    @NotNull
    private String animalKind;

    @NotNull
    private String animalVariety;

    @NotNull
    private String animalSex;

    @NotNull
    private String animalSterilization;

    @NotNull
    private String animalBacterin;
    private String imageUrl;

    private String area;

    private String phone;
    private String description;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
