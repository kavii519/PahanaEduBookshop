package com.pahanaedu.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void testCustomerSettersAndGetters() {
        Customer customer = new Customer();

        customer.setId(1);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("123456789");
        customer.setAddress("123 Main St");
        LocalDateTime now = LocalDateTime.now();
        customer.setCreatedAt(now);
        customer.setUpdatedAt(now);

        assertEquals(1, customer.getId());
        assertEquals("John Doe", customer.getName());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("123456789", customer.getPhone());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals(now, customer.getCreatedAt());
        assertEquals(now, customer.getUpdatedAt());
    }

    @Test
    public void testToStringContainsData() {
        Customer customer = new Customer("Alice", "alice@example.com", "987654321", "456 Park Ave");
        customer.setId(2);

        String output = customer.toString();
        assertTrue(output.contains("Alice"));
        assertTrue(output.contains("alice@example.com"));
    }
}
