package com.company.main;

import com.company.dao.impl.*;
import com.company.dao.inter.*;
import com.company.model.Education;

public class Context {
    public static UserDAOInter instanceUserDAO(){
        return new UserDaoImpl();
    }
    public static UserSkillDAOInter instanceUserSkillDAO(){
        return new UserSkillDAOImpl();
    }
    public static SkillDAOInter instanceSkillDAO(){
        return new SkillDAOImpl();
    }
    public static CityDAOInter instanceCityDAO(){
        return new CityDAOImpl();
    }
    public static UniversityDAOInter instanceUniversityDAO(){
        return new UniversityDAOImpl();
    }
    public static ContactDetailDAOInter instanceContactDetailDAO(){
        return new ContactDetailDAOImpl();
    }
    public static EducationDAOInter instanceEducationDAO(){
        return new EducationDAOImpl();
    }
    public static EmploymentHistoryDAOInter instanceEmploymentHistoryDAO(){
        return new EmploymentHistoryDAOImpl();
    }
}
