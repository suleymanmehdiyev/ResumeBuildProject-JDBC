package com.company.main;

import com.company.dao.inter.*;
import com.company.model.*;

import java.sql.Date;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {

//
//        User u = new User(11,"Süleymannn","Mehdi","djnnsad",new Date(2000-11-11), Gender.MALE.name(),"Subay","Azərbaycanlı","Xeyr","Back end developer","smehdiyev939@gmail.com","12345");
//        UserDAOInter userDAOInter = Context.instanceUserDAO();
//        System.out.println(userDAOInter.getById(48));
//        System.out.println(userDAOInter.getById(35));
//        UserSkillDAOInter userSkillDAOInter = Context.instanceUserSkillDAO();
//        System.out.println(userSkillDAOInter.getAllUserSkill(48));


//        SkillDAOInter skillDAOInter = Context.instanceSkillDAO();
//        System.out.println(skillDAOInter.getByName("Java"));

//        CityDAOInter cityDAOInter = Context.instanceCityDAO();
//        System.out.println(cityDAOInter.getById(1));
//
//        UniversityDAOInter universityDAOInter = Context.instanceUniversityDAO();
//        System.out.println(universityDAOInter.getById(1));

        ContactDetailDAOInter contactDetailDAOInter = Context.instanceContactDetailDAO();
//        ContactDetail cd = new ContactDetail(null,"3243523525","baki",new City(3),new User(38));
        System.out.println(contactDetailDAOInter.getContactDetailById(48));
//        EducationDAOInter educationDAOInter = Context.instanceEducationDAO();
//        System.out.println(educationDAOInter.addEducation(new Education(1,"Master","Special technik and technology",new Date(2022-10-10),new Date(2000-10-10),new University(1),new User(41))));
//        System.out.println(educationDAOInter.getEducationById(1));


//        EmploymentHistoryDAOInter employmentHistory = Context.instanceEmploymentHistoryDAO();
//        EmploymentHistory employmentHistory1 = new EmploymentHistory(1,"Galactech school 2",new Date(2022-02-02),new Date(2022-02-02),"back end developer",new User(1));
//        System.out.println(employmentHistory.getEmploymentHistoryById(1));

//        UserDAOInter userDAOInter = Context.instanceUserDAO();
//        System.out.println(userDAOInter.findByUserEmail("smehdiyev939@gmail.com"));
    }

}
