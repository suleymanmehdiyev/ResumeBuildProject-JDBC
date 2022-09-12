package com.company.model;

public class City {
    private Integer id;
    private String cityName;

    public City() {
    }

    public City(Integer id) {
        this.id = id;
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(Integer id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
