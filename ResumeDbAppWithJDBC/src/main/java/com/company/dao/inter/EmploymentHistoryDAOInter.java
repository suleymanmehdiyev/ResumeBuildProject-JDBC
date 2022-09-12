package com.company.dao.inter;

import com.company.model.Education;
import com.company.model.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDAOInter {
    List<EmploymentHistory> getAllEmploymentHistory(int userId);
    EmploymentHistory getEmploymentHistoryById(int id);
    EmploymentHistory addEmploymentHistory(EmploymentHistory employmentHistory);
    EmploymentHistory updateEmploymentHistory(EmploymentHistory employmentHistory);
    void deleteEmploymentHistory(int userId);
}
