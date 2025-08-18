package com.pahanaedu.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemDTOTest {

    @Test
    public void testConstructorAndGetters() {
        ItemDTO dto = new ItemDTO(1, "Book1", "Java Guide", 2500.0, 50);

        assertEquals(1, dto.getId());
        assertEquals("Book1", dto.getName());
        assertEquals("Java Guide", dto.getDescription());
        assertEquals(2500.0, dto.getPrice(), 0.001);
        assertEquals(50, dto.getStockQuantity());
    }

    @Test
    public void testSetters() {
        ItemDTO dto = new ItemDTO();
        dto.setId(2);
        dto.setName("Book2");
        dto.setDescription("Advanced Programming");
        dto.setPrice(2000.0);
        dto.setStockQuantity(20);

        assertEquals(2, dto.getId());
        assertEquals("Book2", dto.getName());
        assertEquals("Advanced Programming", dto.getDescription());
        assertEquals(2000.0, dto.getPrice(), 0.001);
        assertEquals(20, dto.getStockQuantity());
    }
}
