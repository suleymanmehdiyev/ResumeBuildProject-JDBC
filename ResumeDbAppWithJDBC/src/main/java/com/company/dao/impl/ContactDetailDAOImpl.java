package com.company.dao.impl;

import com.company.dao.inter.ContactDetailDAOInter;
import com.company.dao.inter.UserSkillDAOInter;
import com.company.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContactDetailDAOImpl extends AbstractDAO implements ContactDetailDAOInter {
    private ContactDetail getContactDetail(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String phone_number = rs.getString("phone_number");
        String actual_address = rs.getString("actual_address");
        int regAddress = rs.getInt("reg_address");
        String city_name = rs.getString("city_name");
        int userId = rs.getInt("user_id");
        return new ContactDetail(id,phone_number,actual_address,new City(regAddress,city_name),new User(userId));

    }
    @Override
    public List<ContactDetail> getAllContactDetail() {
        List<ContactDetail> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select cd.*, c.city_name from contactdetail cd\n" +
                    "left join city c on cd.reg_address = c.id\n" +
                    "left join \"user\" u on cd.user_id = u.id ");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                ContactDetail cd = getContactDetail(rs);
                result.add(cd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public ContactDetail getContactDetailById(int user_id) {
        ContactDetail result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select cd.*, c.* from contactdetail cd\n" +
                    "left join city c on cd.reg_address = c.id\n" +
                    "left join \"user\" u on cd.user_id = u.id where user_id=?");
            stmt.setInt(1,user_id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                ContactDetail cd = getContactDetail(rs);
                result = cd;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public ContactDetail addContactDetail(ContactDetail contactDetail) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("insert into contactdetail(phone_number,actual_address,reg_address,user_id) values(?,?,?,?)");

            stmt.setString(1,contactDetail.getPhoneNumber());
            stmt.setString(2,contactDetail.getActualAddress());
            stmt.setInt(3,contactDetail.getRegAddress().getId());
            stmt.setInt(4,contactDetail.getUserId().getId());
             stmt.execute();
             return contactDetail;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ContactDetail updateContactDetail(ContactDetail contactDetail) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("UPDATE contactDetail SET phone_number=?,actual_address=?,reg_address=?,user_id=? where id=?");

            stmt.setString(1,contactDetail.getPhoneNumber());
            stmt.setString(2,contactDetail.getActualAddress());
            stmt.setInt(3,contactDetail.getRegAddress().getId());
            stmt.setInt(4,contactDetail.getUserId().getId());
            stmt.setInt(5,contactDetail.getId());
            stmt.execute();
            return contactDetail;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteContactDetail(int userId) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("DELETE FROM contactdetail where user_id=?");
            stmt.setInt(1, userId);
             stmt.execute();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
