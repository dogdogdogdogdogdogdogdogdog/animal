package com.lovepet.animal.dto;

public class AnimalQueryParams {
    private Integer limit;
    private Integer offset;

    private String shelter;
    private String kind;
    private String area;
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getShelter() {
        return shelter;
    }

    public void setShelter(String shelter) {
        this.shelter = shelter;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
