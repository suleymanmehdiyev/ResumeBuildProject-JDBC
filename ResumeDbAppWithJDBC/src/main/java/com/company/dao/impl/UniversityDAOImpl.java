package com.company.dao.impl;

import com.company.dao.inter.CityDAOInter;
import com.company.dao.inter.UniversityDAOInter;
import com.company.model.City;
import com.company.model.University;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UniversityDAOImpl extends AbstractDAO implements UniversityDAOInter {

    private University getUniversity(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String uniName= rs.getString("uni_name");

        return new University(id,uniName);

    }

    @Override
    public List<University> getAll() {
        List<University> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT uni.* from university uni");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                University uni = getUniversity(rs);
                result.add(uni);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public University getById(int id) {
        University result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT uni.* from university uni where uni.id=?");
            stmt.setInt(1,id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                University us = getUniversity(rs);
                result = us;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public University addUniversity(University uni) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("insert into university(uni_name) values(?)");

            stmt.setString(1, uni.getUniName());
            stmt.execute();
            return uni;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public University updateUniversity(University uni) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("update university set uni_name=? where id=?");

            stmt.setString(1, uni.getUniName());
            stmt.setInt(2, uni.getId());
            stmt.execute();
            return uni;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUniversity(int id) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("DELETE FROM university where id=?");
            stmt.setInt(1, id);
            stmt.execute();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
