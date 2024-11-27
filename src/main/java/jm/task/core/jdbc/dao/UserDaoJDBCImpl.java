package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator;
import org.postgresql.core.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
public class UserDaoJDBCImpl implements UserDao {
    private Connection conn = null;

    public void createUsersTable() {
        try(PreparedStatement statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS users ("+
                "id BIGSERIAL PRIMARY KEY," +
                "name CHARACTER VARYING(255) NOT NULL," +
                "lastName CHARACTER VARYING(255) NOT NULL," +
                "age SMALLINT NOT NULL);")){
            statement.executeUpdate();
            System.out.println("Created Users table");
        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try(PreparedStatement statement = conn.prepareStatement("DROP TABLE users")){
            statement.executeUpdate();
            System.out.println("Dropped Users table");
        } catch (SQLException e) {
            System.out.println("Something went wrong "+e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("Inserted User " + name + " " + lastName + " " + age);
        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM users" +
                "WHERE id = id")) {
            statement.executeUpdate();
            System.out.println("Deleted User " + id);
        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM users")){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
            System.out.println("Loaded Users");
        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM users")){
            statement.executeUpdate();
            System.out.println("Deleted Users");
        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
}
