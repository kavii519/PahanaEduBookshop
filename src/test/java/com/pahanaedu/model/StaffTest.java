package com.pahanaedu.model;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class StaffTest {

    @Test
    public void testDefaultConstructor() {
        Staff staff = new Staff();
        assertEquals("staff", staff.getRole()); // default role
        assertTrue(staff.isNew());
    }

    @Test
    public void testConstructorWithoutId() {
        Staff staff = new Staff("user1", "pass123", "user1@example.com");
        assertEquals("user1", staff.getUsername());
        assertEquals("pass123", staff.getPassword());
        assertEquals("user1@example.com", staff.getEmail());
        assertEquals("staff", staff.getRole());
        assertTrue(staff.isNew());
    }

    @Test
    public void testFullConstructor() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Staff staff = new Staff(1, "user2", "pass456", "user2@example.com", now, now);

        assertEquals(1, staff.getId());
        assertEquals("user2", staff.getUsername());
        assertEquals("pass456", staff.getPassword());
        assertEquals("user2@example.com", staff.getEmail());
        assertEquals("staff", staff.getRole()); // role from default constructor called by full constructor
        assertEquals(now, staff.getCreatedAt());
        assertEquals(now, staff.getUpdatedAt());
        assertFalse(staff.isNew());
    }

    @Test
    public void testSettersAndGetters() {
        Staff staff = new Staff();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        staff.setId(10);
        staff.setUsername("userX");
        staff.setPassword("secret");
        staff.setRole("admin");
        staff.setEmail("userX@example.com");
        staff.setCreatedAt(now);
        staff.setUpdatedAt(now);

        assertEquals(10, staff.getId());
        assertEquals("userX", staff.getUsername());
        assertEquals("secret", staff.getPassword());
        assertEquals("admin", staff.getRole());
        assertEquals("userX@example.com", staff.getEmail());
        assertEquals(now, staff.getCreatedAt());
        assertEquals(now, staff.getUpdatedAt());
        assertFalse(staff.isNew());
    }

    @Test
    public void testToStringContainsProtectedPassword() {
        Staff staff = new Staff("userY", "mypassword", "userY@example.com");
        String str = staff.toString();
        assertTrue(str.contains("[PROTECTED]"));
        assertTrue(str.contains("userY"));
    }
}
