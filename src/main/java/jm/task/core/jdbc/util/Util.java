package jm.task.core.jdbc.util;

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
}
