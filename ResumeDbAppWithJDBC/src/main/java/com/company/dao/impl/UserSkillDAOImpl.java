package com.company.dao.impl;

import com.company.dao.inter.UserSkillDAOInter;
import com.company.model.Skill;
import com.company.model.User;
import com.company.model.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDAOImpl extends AbstractDAO implements UserSkillDAOInter {
    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        int userId = rs.getInt("user_id");
        int skillId = rs.getInt("skill_id");
        String skillName= rs.getString("skill_name");

        return new UserSkill(id,new User(userId),new Skill(skillId,skillName));

    }
    @Override
    public List<UserSkill> getAllUserSkill(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select us.*,s.skill_name\n" +
                    "from userskill us left join \"user\" u on us.user_id = u.id\n" +
                    "left join skill s on us.skill_id = s.id where user_id=?");
            stmt.setInt(1,userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill us = getUserSkill(rs);
                result.add(us);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public UserSkill getById(Integer id) {
        UserSkill result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select u.*,us.id,us.skill_id,s.skill_name\n" +
                    "from userskill us left join \"user\" u on us.user_id = u.id\n" +
                    "left join skill s on us.skill_id = s.id where id=?");
            stmt.setInt(1,id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill us = getUserSkill(rs);
                result = us;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public UserSkill addUserSkill(UserSkill userSkill) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("insert into userskill(user_id,skill_id) values(?,?)");

            stmt.setInt(1,userSkill.getUser().getId());
            stmt.setInt(2,userSkill.getSkill().getId());
            stmt.execute();
            return userSkill;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public UserSkill updateUserSkill(UserSkill userSkill) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("UPDATE userskill SET skill_id=?,user_id=? where id=?");

            stmt.setInt(1,userSkill.getUser().getId());
            stmt.setInt(2,userSkill.getSkill().getId());

            stmt.setInt(3,userSkill.getId());
            stmt.execute();
            return userSkill;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUserSkill(Integer id) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("DELETE FROM userskill where id=?");
            stmt.setInt(1, id);
            stmt.execute();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
