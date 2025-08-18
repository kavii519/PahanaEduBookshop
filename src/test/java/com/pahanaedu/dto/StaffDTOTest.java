package com.pahanaedu.dto;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class StaffDTOTest {

    @Test
    public void testDefaultConstructorAndSetters() {
        StaffDTO dto = new StaffDTO();

        dto.setId(1);
        dto.setUsername("user1");
        dto.setPassword("pass123");
        dto.setRole("staff");
        dto.setEmail("user1@example.com");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        dto.setCreatedAt(now);
        dto.setUpdatedAt(now);

        assertEquals(1, dto.getId());
        assertEquals("user1", dto.getUsername());
        assertEquals("pass123", dto.getPassword());
        assertEquals("staff", dto.getRole());
        assertEquals("user1@example.com", dto.getEmail());
        assertEquals(now, dto.getCreatedAt());
        assertEquals(now, dto.getUpdatedAt());
    }

    @Test
    public void testFullConstructor() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        StaffDTO dto = new StaffDTO(2, "user2", "pass456", "admin", "user2@example.com", now, now);

        assertEquals(2, dto.getId());
        assertEquals("user2", dto.getUsername());
        assertEquals("pass456", dto.getPassword());
        assertEquals("admin", dto.getRole());
        assertEquals("user2@example.com", dto.getEmail());
        assertEquals(now, dto.getCreatedAt());
        assertEquals(now, dto.getUpdatedAt());
    }
}
