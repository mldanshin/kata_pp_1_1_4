package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = """
                    CREATE TABLE IF NOT EXISTS users ( 
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(200) NOT NULL,
                    last_name VARCHAR(200) NOT NULL,
                    age TINYINT NOT NULL
                    )
                    """;
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Table creation error.");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "DROP TABLE IF EXISTS users;";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Table drop error.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User save error.");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM TABLE users WHERE id=?;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Table delete error.");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "SELECT * FROM users;";
                try (ResultSet result = statement.executeQuery(sql)) {
                    while (result.next()) {
                        User user = new User();
                        user.setId(result.getLong("id"));
                        user.setName(result.getString("name"));
                        user.setLastName(result.getString("last_name"));
                        user.setAge(result.getByte("age"));

                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Table clean error.");
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "TRUNCATE TABLE users;";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Table clean error.");
        }
    }
}
