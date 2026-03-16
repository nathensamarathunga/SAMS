// language: java
package com.sams.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // Configure these for your local MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/sams?useSSL=false&serverTimezone=UTC";
    private static final String USER = "sams_user";
    private static final String PASS = "sams_pass";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
