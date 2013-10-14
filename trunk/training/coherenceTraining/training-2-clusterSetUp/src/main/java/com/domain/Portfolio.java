package com.domain;

import java.io.Serializable;

public class Portfolio implements Serializable {

    private Integer id;

    private String name;

    public Portfolio(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
