package service.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionToMySQL {
    static final String URL = "jdbc:mysql://localhost:3306/laptop_store";
    static final String USERNAME = "root";
    static final String PASSWORD = "1234";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            return null;
        }
    }
}
