package com.pahanaedu.dto;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CustomerDTOTest {

    @Test
    public void testSettersAndGetters() {
        CustomerDTO dto = new CustomerDTO();

        dto.setId(5);
        dto.setName("Bob");
        dto.setEmail("bob@example.com");
        dto.setPhone("555-1111");
        dto.setAddress("789 Elm St");
        LocalDateTime now = LocalDateTime.now();
        dto.setCreatedAt(now);

        assertEquals(5, dto.getId());
        assertEquals("Bob", dto.getName());
        assertEquals("bob@example.com", dto.getEmail());
        assertEquals("555-1111", dto.getPhone());
        assertEquals("789 Elm St", dto.getAddress());
        assertEquals(now, dto.getCreatedAt());
    }
}
