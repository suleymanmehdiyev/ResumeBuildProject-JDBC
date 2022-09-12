package com.company.dao.impl;

import com.company.dao.inter.SkillDAOInter;
import com.company.model.Skill;
import com.company.model.User;
import com.company.model.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SkillDAOImpl extends AbstractDAO implements SkillDAOInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String skillName= rs.getString("skill_name");

        return new Skill(id,skillName);

    }
    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT s.* from skill s");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill us = getSkill(rs);
                result.add(us);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Skill getById(int id) {
        Skill result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT s.* from skill s");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill us = getSkill(rs);
                result = us;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Skill addSkill(Skill skill) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("insert into skill(skill_name) values(?)");

            stmt.setString(1, skill.getSkillName());
            stmt.execute();
            return skill;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Skill updateSkill(Skill skill) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("update skill set skill_name=? where id=?");

            stmt.setString(1, skill.getSkillName());
            stmt.setInt(2, skill.getId());
            stmt.execute();
            return skill;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public void deleteSkill(int id) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("DELETE FROM skill where id=?");
            stmt.setInt(1, id);
            stmt.execute();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Skill getByName(String name) {
        Skill result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT s.* from skill s where s.skill_name = ?");
            stmt.setString(1,name);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill us = getSkill(rs);
                result = us;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
