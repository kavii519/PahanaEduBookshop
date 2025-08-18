package com.pahanaedu.model;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testNoArgConstructorAndSettersGetters() {
        Item item = new Item();

        item.setId(1);
        item.setName("Laptop");
        item.setDescription("Gaming Laptop");
        item.setPrice(1500.50);
        item.setStockQuantity(10);
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Timestamp updated = new Timestamp(System.currentTimeMillis());
        item.setCreatedAt(created);
        item.setUpdatedAt(updated);

        assertEquals(1, item.getId());
        assertEquals("Laptop", item.getName());
        assertEquals("Gaming Laptop", item.getDescription());
        assertEquals(1500.50, item.getPrice(), 0.001);
        assertEquals(10, item.getStockQuantity());
        assertEquals(created, item.getCreatedAt());
        assertEquals(updated, item.getUpdatedAt());
    }

    @Test
    public void testAllArgsConstructor() {
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Timestamp updated = new Timestamp(System.currentTimeMillis());

        Item item = new Item(1, "Laptop", "Gaming Laptop", 1500.50, 10, created, updated);

        assertEquals(1, item.getId());
        assertEquals("Laptop", item.getName());
        assertEquals("Gaming Laptop", item.getDescription());
        assertEquals(1500.50, item.getPrice(), 0.001);
        assertEquals(10, item.getStockQuantity());
        assertEquals(created, item.getCreatedAt());
        assertEquals(updated, item.getUpdatedAt());
    }

    @Test
    public void testPartialArgsConstructor() {
        // this covers your constructor: Item(int i, String s, double v, int i1)
        Item item = new Item(1, "Phone", 799.99, 50);

        assertEquals(1, item.getId());
        assertEquals("Phone", item.getName());
        assertEquals(799.99, item.getPrice(), 0.001);
        assertEquals(50, item.getStockQuantity());

        // The other fields are still default null/0
        assertNull(item.getDescription());
        assertNull(item.getCreatedAt());
        assertNull(item.getUpdatedAt());
    }
}
