package com.lovepet.animal.model;

public class AnimalFuneral {

    // 在型別中定義 id 並在查詢結果返回 id 資料
    // 此 id 進一步設於 HTML 標籤屬性 id 中
    // 經設定過 id 的標籤具唯一識別性
    // 可為個別標籤的父子元素設計不同內容的彈出式互動視窗
    // ※ 請記得 RowMapper 中也要設定查詢欄位
    private Integer id;
    private String agent;
    private String tel;
    private String area;
    private String address;
    private String link;
    private String service;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
