package com.company.dao.inter;

import com.company.model.City;
import com.company.model.Skill;

import java.util.List;

public interface CityDAOInter {
    List<City> getAll();
    City getById(int id);
    City addCity(City city);
    City updateCity(City city);
    void deleteCity(int id);
}
