package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.UserDAOInter;
import com.company.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements UserDAOInter {
    private User getUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String uname = rs.getString("uname");
        String surname = rs.getString("surname");
        String patronymic = rs.getString("patronymic");
        Date birthdate = rs.getDate("birthdate");
        String gender = rs.getString("gender");
        String martialStatus = rs.getString("marital_status");
        String citizenship = rs.getString("citizenship");
        String military = rs.getString("military");
        String about = rs.getString("about");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String phoneNumber = rs.getString("phone_number");
        String actualAddress = rs.getString("actual_address");
        int regAddress = rs.getInt("reg_address");
        String cityName = rs.getString("city_name");
        String degree = rs.getString("degree");
        String speciality = rs.getString("speciality");
        Date beginDateEdu = rs.getDate("begin_date");
        Date endDateEdu = rs.getDate("end_date");
        int uniId = rs.getInt("university_id");
        String uniName = rs.getString("uni_name");
        String skillName = rs.getString("skill_name");
        String companyName = rs.getString("company_name");
        String jobDesc = rs.getString("job_description");
        String position = rs.getString("job_position");
        Date beginDateEmploy = rs.getDate("begin_date");
        Date endDateEmploy = rs.getDate("end_date");

        ContactDetail cd = new ContactDetail(null,phoneNumber,actualAddress,new City(regAddress,cityName),new User(id));
        List<Education> edu = new ArrayList<>();
        edu.add(new Education(null,degree,speciality,beginDateEdu,endDateEdu,new University(uniId,uniName),new User(id)));

        List<EmploymentHistory> emp = new ArrayList<>();
        emp.add(new EmploymentHistory(null,companyName,position,beginDateEmploy,endDateEmploy,jobDesc,new User(id)));

        List<UserSkill> us =  new ArrayList<>();
        us.add(new UserSkill(null,new User(id),new Skill(null,skillName)));

        User u =new User(id,uname,surname,patronymic,birthdate,gender, martialStatus,citizenship, military,
                about,email,password,cd,us,emp,edu);
        return u;

    }
    private User getUserSimple(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String uname = rs.getString("uname");
        String surname = rs.getString("surname");
        String patronymic = rs.getString("patronymic");
        Date birthdate = rs.getDate("birthdate");
        String gender = rs.getString("gender");
        String martialStatus = rs.getString("marital_status");
        String citizenship = rs.getString("citizenship");
        String military = rs.getString("military");
        String about = rs.getString("about");
        String email = rs.getString("email");
        String password = rs.getString("password");

        User u =new User(id,uname,surname,patronymic,birthdate,gender, martialStatus,citizenship, military,
                about,email,password);
        return u;

    }
    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try(Connection c=connect()){
            PreparedStatement stmt = c.prepareStatement("SELECT u.*,\n" +
                    "       c.phone_number, c.actual_address,\n" +
                    "       cit.city_name,\n" +
                    "       e.degree, e.speciality, e.begin_date, e.end_date,\n" +
                    "       ski.skill_name,\n" +
                    "       eh.*" +
                    "from \"user\" u\n" +
                    "         left join contactdetail c on u.id = c.user_id\n" +
                    "         left join city cit on cit.id = c.id\n" +
                    "         left join education e on u.id = e.user_id\n" +
                    "         left join university uni on uni.id = u.id\n" +
                    "         left join userskill us on u.id = us.user_id\n" +
                    "         left join skill ski on ski.id = us.skill_id\n" +
                    "         left join employment_history eh on u.id = eh.user_id");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                User u =getUser(rs);
                result.add(u);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getAllNameAndSurname(String uname, String surname, String patronymic) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            String sql = "select u.* from \"user\" u where 1 = 1 ";
            if (uname != null && !(uname.trim().isEmpty())) {
                sql += " and u.uname = ? ";
            }
            if (surname != null && !(surname.trim().isEmpty())) {
                sql += " and u.surname = ? ";
            }
            if (patronymic != null && !(patronymic.trim().isEmpty())) {
                sql += " and u.patronymic = ? ";
            }
            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;//i indeksi bildirir
            if (uname != null && !(uname.trim().isEmpty())) {
                stmt.setString(i, uname);
                i++;
            }
            if (surname != null && !(surname.trim().isEmpty())) {
                stmt.setString(i, surname);
                i++;
            }
            if (patronymic != null && !(patronymic.trim().isEmpty())) {
                stmt.setString(i, patronymic);
                i++;
            }
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUserSimple(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int id) {
        User result = null;
        try(Connection c=connect()){
            PreparedStatement stmt = c.prepareStatement("SELECT u.*, c.*,cit.*,e.*,\n" +
                    "    ski.*,eh.company_name,eh.begin_date,eh.end_date,eh.job_description,eh.job_position" +
                    ",uni.uni_name from \"user\" u\n" +
                    "    left join contactdetail c on u.id = c.user_id\n" +
                    "         left join city cit on cit.id = c.reg_address\n" +
                    "    left join education e on u.id = e.user_id\n" +
                    "    left join university uni on uni.id = e.university_id\n" +
                    "    left join userskill us on u.id = us.user_id\n" +
                    "    left join skill ski on ski.id = us.skill_id\n" +
                    "    left join employment_history eh on u.id = eh.user_id where u.id =?");
            stmt.setInt(1,id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                result = getUser(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
private BCrypt.Hasher bCrypt = BCrypt.withDefaults();


    @Override
    public User addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" insert into \"user\"(uname,surname,patronymic,birthdate,gender,marital_status,citizenship,military,about,email,password) values (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, u.getUname());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPatronymic());
            stmt.setDate(4,   u.getBirthdate());
            stmt.setString(5, u.getGender());
            stmt.setString(6, u.getMaritalStatus());
            stmt.setString(7, u.getCitizenship());
            stmt.setString(8, u.getMilitary());
            stmt.setString(9, u.getAbout());
            stmt.setString(10, u.getEmail());
            stmt.setString(11, bCrypt.hashToString(4,u.getPassword().toCharArray()));
            stmt.execute();
            return u;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" update \"user\" set uname=?,surname=?,patronymic=?,birthdate=?,gender=?,marital_status=?,citizenship=?,military=?,about=? where id=?");
            stmt.setString(1, u.getUname());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPatronymic());
            stmt.setDate(4, u.getBirthdate());
            stmt.setString(5, u.getGender());
            stmt.setString(6, u.getMaritalStatus());
            stmt.setString(7, u.getCitizenship());
            stmt.setString(8, u.getMilitary());
            stmt.setString(9, u.getAbout());
            stmt.setInt(10,u.getId());
            stmt.execute();
            return u;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from \"user\" where id =?");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @Override
    public User findByUserEmail(String email) {
        User result = null;
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select u.* from \"user\" u where email=?");
            stmt.setString(1,email);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                result=getUserSimple(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
