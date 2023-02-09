package com.lovepet.animal.dto;

import org.springframework.web.multipart.MultipartFile;


public class MissingAnimalRequest {
    private  String photoUrl;
    private Integer userId;
    private String missingKind;
    private String missingVariety;
    private String missingSex;
    private String missingBodyShape;
    private String missingColor;
    private String missingAge;
    private String missingDate;
    private String missingPlace;
    private String missingRemark;
    private MultipartFile missingAnimalPhoto;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMissingKind() {
        return missingKind;
    }

    public void setMissingKind(String missingKind) {
        this.missingKind = missingKind;
    }

    public String getMissingVariety() {
        return missingVariety;
    }

    public void setMissingVariety(String missingVariety) {
        this.missingVariety = missingVariety;
    }

    public String getMissingSex() {
        return missingSex;
    }

    public void setMissingSex(String missingSex) {
        this.missingSex = missingSex;
    }

    public String getMissingBodyShape() {
        return missingBodyShape;
    }

    public void setMissingBodyShape(String missingBodyShape) {
        this.missingBodyShape = missingBodyShape;
    }

    public String getMissingColor() {
        return missingColor;
    }

    public void setMissingColor(String missingColor) {
        this.missingColor = missingColor;
    }

    public String getMissingAge() {
        return missingAge;
    }

    public void setMissingAge(String missingAge) {
        this.missingAge = missingAge;
    }

    public String getMissingDate() {
        return missingDate;
    }

    public void setMissingDate(String missingDate) {
        this.missingDate = missingDate;
    }

    public String getMissingPlace() {
        return missingPlace;
    }

    public void setMissingPlace(String missingPlace) {
        this.missingPlace = missingPlace;
    }

    public String getMissingRemark() {
        return missingRemark;
    }

    public void setMissingRemark(String missingRemark) {
        this.missingRemark = missingRemark;
    }

    public MultipartFile getMissingAnimalPhoto() {
        return missingAnimalPhoto;
    }

    public void setMissingAnimalPhoto(MultipartFile missingAnimalPhoto) {
        this.missingAnimalPhoto = missingAnimalPhoto;
    }
}
