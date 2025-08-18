package com.pahanaedu.dto;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class BillDTOTest {

    private BillDTO billDTO;

    @Before
    public void setUp() {
        billDTO = new BillDTO();
    }

    @Test
    public void testSettersAndGetters() {
        billDTO.setBillId(1);
        assertEquals(1, billDTO.getBillId());

        billDTO.setItemIds("1,2,3");
        assertEquals("1,2,3", billDTO.getItemIds());

        billDTO.setCustomerId(101);
        assertEquals(101, billDTO.getCustomerId());

        billDTO.setCustomerName("John Doe");
        assertEquals("John Doe", billDTO.getCustomerName());

        Timestamp now = new Timestamp(System.currentTimeMillis());
        billDTO.setBillDateTime(now);
        assertEquals(now, billDTO.getBillDateTime());

        billDTO.setTotalAmount(500.75);
        assertEquals(500.75, billDTO.getTotalAmount(), 0.001);
    }

    @Test
    public void testGetQuantities() {
        // Currently getQuantities() always returns null
        assertNull(billDTO.getQuantities());
    }
}
