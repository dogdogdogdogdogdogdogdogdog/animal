package com.lovepet.animal.dto;

import org.springframework.web.multipart.MultipartFile;


public class MissingAnimalRequest {

    private Integer missingId;
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

    public Integer getMissingId() {
        return missingId;
    }

    public void setMissingId(Integer missingId) {
        this.missingId = missingId;
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
