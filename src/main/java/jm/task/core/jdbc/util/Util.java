package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;
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

    /* ------------------ Hibernate ------------------ */


        public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/users_db");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "FPTmca9FPTmca9()123");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                properties.put(Environment.SHOW_SQL, "true");

                //properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "threat");

                properties.put(Environment.HBM2DDL_AUTO, "none");

                configuration.setProperties(properties);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return sessionFactory;


    }

}
