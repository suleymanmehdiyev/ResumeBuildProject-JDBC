package com.company.model;

public class University {
    private Integer id;
    private String uniName;

    public University() {
    }

    public University(Integer id) {
        this.id = id;
    }

    public University(Integer id, String uniName) {
        this.id = id;
        this.uniName = uniName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    @Override
    public String toString() {
        return uniName;
    }
}
