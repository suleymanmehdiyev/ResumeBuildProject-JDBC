package com.company.dao.inter;

import com.company.model.User;

import java.util.List;

public interface UserDAOInter {
    List<User> getAll();
    List<User> getAllNameAndSurname(String name,String surname,String patronymic);

    User getById(int id);
    User addUser(User u);
    User updateUser(User u);
    void deleteUser(int id);
    User findByUserEmail(String email);
}
