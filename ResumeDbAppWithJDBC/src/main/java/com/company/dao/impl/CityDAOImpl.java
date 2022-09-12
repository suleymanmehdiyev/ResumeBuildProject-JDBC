package com.company.dao.impl;

import com.company.dao.inter.CityDAOInter;
import com.company.model.City;
import com.company.model.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl extends AbstractDAO implements CityDAOInter {

    private City getCity(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String cityName= rs.getString("city_name");

        return new City(id,cityName);

    }

    @Override
    public List<City> getAll() {
        List<City> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT c.* from city c");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                City city = getCity(rs);
                result.add(city);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public City getById(int id) {
        City result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT c.* from city c where c.id=?");
            stmt.setInt(1,id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                City us = getCity(rs);
                result = us;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public City addCity(City city) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("insert into city(city_name) values(?)");

            stmt.setString(1, city.getCityName());
            stmt.execute();
            return city;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public City updateCity(City city) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("update city set city_name=? where id=?");

            stmt.setString(1, city.getCityName());
            stmt.setInt(2, city.getId());
            stmt.execute();
            return city;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCity(int id) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("DELETE FROM city where id=?");
            stmt.setInt(1, id);
            stmt.execute();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
