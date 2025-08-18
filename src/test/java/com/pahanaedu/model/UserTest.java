package com.pahanaedu.model;

import org.junit.Test;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();
        LocalDateTime now = LocalDateTime.now();

        user.setId(1);
        user.setUsername("admin");
        user.setPassword("admin123");
        user.setRole("admin");
        user.setEmail("admin@example.com");
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        assertEquals(1, user.getId());
        assertEquals("admin", user.getUsername());
        assertEquals("admin123", user.getPassword());
        assertEquals("admin", user.getRole());
        assertEquals("admin@example.com", user.getEmail());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    public void testUserParameterizedConstructor() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1, "admin", "admin123", "admin", "admin@example.com", now, now);

        assertEquals("admin", user.getUsername());
        assertEquals("admin123", user.getPassword());
        assertEquals("admin", user.getRole());
        assertEquals("admin@example.com", user.getEmail());
    }
}
