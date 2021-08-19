package jm.task.core.jdbc.util;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private final String PASSWORD = "FPTmca9FPTmca9()123";
    private final String USER = "root";
    private final String URL = "jdbc:mysql://localhost:3306/users_db";
    private Connection connection;


    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public  Connection getConnection() {
        return connection;
    }

    Configuration cfg = new Configuration();

}
