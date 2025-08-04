package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;
import com.pahanaedu.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;

public class UserDAOImpl implements UserDAO {
    private static final String FIND_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

    @Override
    public User findByUsername(String username) {
        User user = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME)) {

            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));
                    user.setEmail(resultSet.getString("email"));
                    user.setCreatedAt(resultSet.getObject("created_at", LocalDateTime.class));
                    user.setUpdatedAt(resultSet.getObject("updated_at", LocalDateTime.class));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean validateUser(String username, String password) {
        User user = findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}