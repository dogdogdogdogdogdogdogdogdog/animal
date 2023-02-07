package com.lovepet.animal.util;

import java.util.List;

public class MissingPage<T> {

    private Integer limit;
    private Integer offset;
    private Integer total;
    private List<T> missingAnimals;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getMissingAnimals() {
        return missingAnimals;
    }

    public void setMissingAnimals(List<T> missingAnimals) {
        this.missingAnimals = missingAnimals;
    }
}
