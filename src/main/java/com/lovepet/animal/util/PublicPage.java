package com.lovepet.animal.util;


import java.util.List;

public class PublicPage<T> {

    private List<T> publicAnimals;
    private List<String> page;


    public List<String> getPage() {
        return page;
    }

    public void setPage(List<String> page) {
        this.page = page;
    }

    public List<T> getPublicAnimals() {
        return publicAnimals;
    }

    public void setPublicAnimals(List<T> publicAnimals) {
        this.publicAnimals = publicAnimals;
    }
}
