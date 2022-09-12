package com.company.dao.inter;

import com.company.model.ContactDetail;
import com.company.model.Education;

import java.util.List;

public interface EducationDAOInter {
    List<Education> getAllEducation(int userId);
    Education getEducationById(int id);
    Education addEducation(Education education);
    Education updateEducation(Education education);
    void deleteEducation(int userId);
}
