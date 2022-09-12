package com.company.dao.impl;

import com.company.dao.inter.ContactDetailDAOInter;
import com.company.dao.inter.EducationDAOInter;
import com.company.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EducationDAOImpl extends AbstractDAO implements EducationDAOInter {
    private Education getEducation(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String degree = rs.getString("degree");
        String speciality = rs.getString("speciality");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        int universityId = rs.getInt("university_id");
        String uniName = rs.getString("uni_name");
        int userId = rs.getInt("user_id");
        return new Education(id,degree,speciality,beginDate,endDate,new University(universityId,uniName),new User(userId));

    }
    @Override
    public List<Education> getAllEducation(int userId) {
        List<Education> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select edu.*,\n" +
                    "       uni.uni_name from education edu\n" +
                    "left join university uni on edu.university_id = uni.id\n" +
                    "left join \"user\" u on edu.user_id = u.id where user_id=?");
            stmt.setInt(1,userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Education edu = getEducation(rs);
                result.add(edu);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Education getEducationById(int userId) {
        Education result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select edu.*,\n" +
                    "       uni.uni_name from education edu\n" +
                    "left join university uni on edu.university_id = uni.id\n" +
                    "left join \"user\" u on edu.user_id = u.id where edu.user_id = ?");
            stmt.setInt(1,userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Education edu = getEducation(rs);
                result = edu;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Education addEducation(Education education) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("insert into education(degree,speciality,begin_date,end_date,university_id,user_id) values(?,?,?,?,?,?)");

            stmt.setString(1,education.getDegree());
            stmt.setString(2,education.getSpeciality());
            stmt.setDate(3,education.getBeginDate());
            stmt.setDate(4,education.getEndDate());
            stmt.setInt(5,education.getUniversityId().getId());
            stmt.setInt(6,education.getUserId().getId());
            stmt.execute();
            return education;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Education updateEducation(Education education) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("UPDATE education SET degree=?,speciality=?,begin_date=?,end_date=?,university_id=?,user_id=? where id=?");

            stmt.setString(1,education.getDegree());
            stmt.setString(2,education.getSpeciality());
            stmt.setDate(3,education.getBeginDate());
            stmt.setDate(4,education.getEndDate());
            stmt.setInt(5,education.getUniversityId().getId());
            stmt.setInt(6,education.getUserId().getId());
            stmt.setInt(7,education.getId());
            stmt.execute();
            return education;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteEducation(int userId) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("DELETE FROM education where user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
