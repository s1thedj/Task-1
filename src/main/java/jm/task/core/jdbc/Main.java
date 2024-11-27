package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        try(Connection conn = Util.getConnection()) {
            UserDao user = new UserDaoJDBCImpl(conn);
            user.createUsersTable();
            user.saveUser("Игорь","Авралов",(byte)18);
            user.saveUser("Егор","Иванов",(byte)21);
            user.saveUser("Иван","Иванов",(byte)22);
            user.saveUser("Лариса","Капралова",(byte)25);
            System.out.println("Table Users: \n" + user.getAllUsers());
            user.cleanUsersTable();
            user.dropUsersTable();

        } catch (Exception e){
            System.out.println("Something went wrong with connection: " + e.getMessage());
        }
    }
}