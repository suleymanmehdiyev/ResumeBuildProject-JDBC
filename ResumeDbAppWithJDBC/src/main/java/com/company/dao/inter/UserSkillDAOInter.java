package com.company.dao.inter;

import com.company.model.UserSkill;

import java.util.List;

public interface UserSkillDAOInter {
    List<UserSkill> getAllUserSkill(int userId);
    UserSkill getById(Integer id);
    UserSkill addUserSkill(UserSkill userSkill);
    UserSkill updateUserSkill(UserSkill userSkill);
    void deleteUserSkill(Integer userId);
}
