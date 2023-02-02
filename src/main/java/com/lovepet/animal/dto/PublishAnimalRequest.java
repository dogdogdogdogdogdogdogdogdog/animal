package com.lovepet.animal.dto;


import org.springframework.web.multipart.MultipartFile;

public class PublishAnimalRequest {
    private Integer id;
    private MultipartFile animalPhoto;
    private String kind;
    private String variety;
    private String sex;
    private String bodyShape;
    private String color;
    private String age;
    private String ligation;
    private String address;
    private String remark;
    private Integer item;

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartFile getAnimalPhoto() {
        return animalPhoto;
    }

    public void setAnimalPhoto(MultipartFile animalPhoto) {
        this.animalPhoto = animalPhoto;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBodyShape() {
        return bodyShape;
    }

    public void setBodyShape(String bodyShape) {
        this.bodyShape = bodyShape;
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

    public String getLigation() {
        return ligation;
    }

    public void setLigation(String ligation) {
        this.ligation = ligation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
