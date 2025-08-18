package com.pahanaedu.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class BillTest {

    private Bill bill;

    @Before
    public void setUp() {
        // Initialize a Bill object before each test
        bill = new Bill("1,2,3", 101, 250.50);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("1,2,3", bill.getItemIds());
        assertEquals(101, bill.getCustomerId());
        assertEquals(250.50, bill.getTotalAmount(), 0.001);
        assertNull(bill.getBillDateTime());
        assertEquals(0, bill.getBillId());
    }

    @Test
    public void testSettersAndGetters() {
        bill.setBillId(10);
        assertEquals(10, bill.getBillId());

        bill.setItemIds("4,5,6");
        assertEquals("4,5,6", bill.getItemIds());

        bill.setCustomerId(202);
        assertEquals(202, bill.getCustomerId());

        Timestamp now = new Timestamp(System.currentTimeMillis());
        bill.setBillDateTime(now);
        assertEquals(now, bill.getBillDateTime());

        bill.setTotalAmount(999.99);
        assertEquals(999.99, bill.getTotalAmount(), 0.001);
    }

    @Test
    public void testOptionalMethods() {
        // These methods currently return null or do nothing
        assertNull(bill.getCustomerName());
        assertNull(bill.getCustomer());
        bill.setQuantities(5.0); // No effect, just make sure it doesn't crash
    }
}
