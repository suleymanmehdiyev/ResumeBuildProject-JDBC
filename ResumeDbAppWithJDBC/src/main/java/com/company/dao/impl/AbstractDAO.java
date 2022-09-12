package com.company.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {
    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");//JavaEE ucun manual qurasdirilmalidir
        String url = "jdbc:postgresql://localhost/resume1";
        String username = "postgres";
        String password = "admin";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
