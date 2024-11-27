package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String pass = "lovealyona2002";
        try {
            conn = DriverManager.getConnection(url, username, pass);
            System.out.println("Connection to database is successful!");
        } catch (SQLException e) {
                System.out.println("Error connecting to database");
                throw new RuntimeException(e);
            }
        return conn;
    }

    public static Session getSession() {
        Session session = null;
        try {
            Configuration configuration = new Configuration().addAnnotatedClass(User.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.getCurrentSession();
            System.out.println("Connection to database is successful!");
        } catch (Exception e) {
            System.out.println("Error connecting to database");
        }
        return session;
    }

}
