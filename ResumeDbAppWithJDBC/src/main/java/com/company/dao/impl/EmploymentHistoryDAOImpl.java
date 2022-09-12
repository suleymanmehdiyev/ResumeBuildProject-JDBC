package com.company.dao.impl;

import com.company.dao.inter.EmploymentHistoryDAOInter;
import com.company.model.EmploymentHistory;
import com.company.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmploymentHistoryDAOImpl extends AbstractDAO implements EmploymentHistoryDAOInter {
    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String companyName = rs.getString("company_name");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String jobDesc = rs.getString("job_description");
        String position = rs.getString("job_position");
        int userId = rs.getInt("user_id");
        return new EmploymentHistory(id,companyName,position,beginDate,endDate,jobDesc,new User(userId));

    }
    @Override
    public List<EmploymentHistory> getAllEmploymentHistory(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select emp.* from employment_history emp\n" +
                    "left join \"user\" u on emp.user_id = u.id where user_id=?");
            stmt.setInt(1,userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory edu = getEmploymentHistory(rs);
                result.add(edu);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public EmploymentHistory getEmploymentHistoryById(int userId) {
        EmploymentHistory result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select emp.* from employment_history emp\n" +
                    "left join \"user\" u on emp.user_id = u.id where emp.user_id=?");
            stmt.setInt(1,userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);
                result = emp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public EmploymentHistory addEmploymentHistory(EmploymentHistory employmentHistory) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("insert into employment_history(company_name,job_position,begin_date,end_date,job_description,user_id) values(?,?,?,?,?,?)");

            stmt.setString(1,employmentHistory.getCompanyName());
            stmt.setString(2,employmentHistory.getPosition());
            stmt.setDate(3, employmentHistory.getBeginDate());
            stmt.setDate(4,employmentHistory.getEndDate());
            stmt.setString(5,employmentHistory.getJobDesc());
            stmt.setInt(6,employmentHistory.getUserId().getId());
            stmt.execute();
            return employmentHistory;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public EmploymentHistory updateEmploymentHistory(EmploymentHistory employmentHistory) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("UPDATE employment_history SET company_name=?,job_position=?,begin_date=?,end_date=?,job_description=?,user_id=? where id=?");

            stmt.setString(1,employmentHistory.getCompanyName());
            stmt.setString(2,employmentHistory.getPosition());
            stmt.setDate(3,employmentHistory.getBeginDate());
            stmt.setDate(4,employmentHistory.getEndDate());
            stmt.setString(5,employmentHistory.getJobDesc());
            stmt.setInt(6,employmentHistory.getUserId().getId());
            stmt.setInt(7,employmentHistory.getId());
            stmt.execute();
            return employmentHistory;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteEmploymentHistory(int userId) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("DELETE FROM employment_history where user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
