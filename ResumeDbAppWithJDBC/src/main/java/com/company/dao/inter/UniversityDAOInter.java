package com.company.dao.inter;

import com.company.model.City;
import com.company.model.University;

import java.util.List;

public interface UniversityDAOInter {
    List<University> getAll();
    University getById(int id);
    University addUniversity(University university);
    University updateUniversity(University university);
    void deleteUniversity(int id);
}
