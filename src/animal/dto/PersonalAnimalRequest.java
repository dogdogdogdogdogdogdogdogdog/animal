package com.lovepet.animal.dto;

import javax.validation.constraints.NotNull;

public class PersonalAnimalRequest {

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
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
