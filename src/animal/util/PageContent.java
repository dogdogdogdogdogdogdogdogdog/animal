package com.lovepet.animal.util;

import com.lovepet.animal.model.AnimalData;

import java.util.List;

public class PageContent {
   private List<Integer> pages;
   private List<AnimalData> animals;

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public List<AnimalData> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalData> animals) {
        this.animals = animals;
    }
}
