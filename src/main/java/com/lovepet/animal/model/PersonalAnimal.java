package com.lovepet.animal.model;

import java.util.Date;

public class PersonalAnimal {

    private Integer animalId;
    private String animalKind;
    private String animalVariety;
    private String animalSex;
    private String animalSterilization;
    private String animalBacterin;
    private String imageUrl;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
