package com.company.dao.inter;

import com.company.model.Skill;
import com.company.model.User;

import java.util.List;

public interface SkillDAOInter {
    List<Skill> getAll();
    Skill getById(int id);
    Skill addSkill(Skill u);
    Skill updateSkill(Skill u);
    void deleteSkill(int id);
    Skill getByName(String name);
}
