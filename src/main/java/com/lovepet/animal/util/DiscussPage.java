package com.lovepet.animal.util;

import java.util.List;

public class DiscussPage<T> {


    private List<T> personalForums;
    private List<String> page;
    private Integer total;



    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getPersonalForums() {
        return personalForums;
    }

    public void setPersonalForums(List<T> personalForums) {
        this.personalForums = personalForums;
    }

    public List<String> getPage() {
        return page;
    }

    public void setPage(List<String> page) {
        this.page = page;
    }
}
