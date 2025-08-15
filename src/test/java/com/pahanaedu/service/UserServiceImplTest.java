package com.pahanaedu.service;

import com.pahanaedu.dto.UserDTO;
import com.pahanaedu.model.User;
import com.pahanaedu.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();

        // Prepare test data
        try (Connection conn = com.pahanaedu.util.DBConnection.getConnection()) {
            conn.createStatement().execute("DELETE FROM users");
            String sql = "INSERT INTO users (username, password, role, email, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, NOW(), NOW())";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "testuser");
                ps.setString(2, "pass123");
                ps.setString(3, "admin");
                ps.setString(4, "test@example.com");
                ps.executeUpdate();
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        try (Connection conn = com.pahanaedu.util.DBConnection.getConnection()) {
            conn.createStatement().execute("DELETE FROM users");
        }
    }

    @Test
    public void testAuthenticateValidUser() {
        UserDTO dto = new UserDTO("testuser", "pass123");
        User user = userService.authenticate(dto);
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
    }

    @Test
    public void testAuthenticateInvalidPassword() {
        UserDTO dto = new UserDTO("testuser", "wrongpass");
        User user = userService.authenticate(dto);
        assertNull(user);
    }

    @Test
    public void testAuthenticateUserNotFound() {
        UserDTO dto = new UserDTO("unknown", "pass123");
        User user = userService.authenticate(dto);
        assertNull(user);
    }

    @Test
    public void testAuthenticateNullValues() {
        assertNull(userService.authenticate(null));
        assertNull(userService.authenticate(new UserDTO(null, "pass")));
        assertNull(userService.authenticate(new UserDTO("name", null)));
    }
}

