package com.pahanaedu.dao.impl;

import com.pahanaedu.model.Bill;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BillDAOImplTest {

    private BillDAOImpl billDAO;

    @Before
    public void setUp() {
        billDAO = new BillDAOImpl();
    }

    @Test
    public void testCreateBill() throws SQLException {
        Bill bill = new Bill("1,2,3", 101, 250.50);
        int billId = billDAO.createBill(bill);

        assertTrue("Bill ID should be greater than 0", billId > 0);

        // Cleanup
        billDAO.deleteBill(billId);
    }

    @Test
    public void testGetBillById() throws SQLException {
        // Create a test bill
        Bill bill = new Bill("4,5,6", 102, 300.75);
        int billId = billDAO.createBill(bill);

        Bill fetchedBill = billDAO.getBillById(billId);
        assertNotNull("Fetched bill should not be null", fetchedBill);
        assertEquals("Item IDs should match", "4,5,6", fetchedBill.getItemIds());
        assertEquals(102, fetchedBill.getCustomerId());
        assertEquals(300.75, fetchedBill.getTotalAmount(), 0.001);

        // Cleanup
        billDAO.deleteBill(billId);
    }

    @Test
    public void testGetAllBills() throws SQLException {
        // Create a test bill
        Bill bill = new Bill("7,8,9", 103, 150.25);
        int billId = billDAO.createBill(bill);

        List<Bill> bills = billDAO.getAllBills();
        assertNotNull("Bills list should not be null", bills);
        assertTrue("Bills list should contain at least one bill", bills.size() > 0);

        // Cleanup
        billDAO.deleteBill(billId);
    }

    @Test
    public void testUpdateBill() throws SQLException {
        // Create a test bill
        Bill bill = new Bill("10,11,12", 104, 400.00);
        int billId = billDAO.createBill(bill);

        // Update the bill
        bill.setBillId(billId);
        bill.setTotalAmount(450.50);
        bill.setItemIds("13,14,15");

        boolean updated = billDAO.updateBill(bill);
        assertTrue("Bill should be updated successfully", updated);

        Bill updatedBill = billDAO.getBillById(billId);
        assertEquals(450.50, updatedBill.getTotalAmount(), 0.001);
        assertEquals("13,14,15", updatedBill.getItemIds());

        // Cleanup
        billDAO.deleteBill(billId);
    }

    @Test
    public void testDeleteBill() throws SQLException {
        // Create a test bill
        Bill bill = new Bill("16,17,18", 105, 500.00);
        int billId = billDAO.createBill(bill);

        boolean deleted = billDAO.deleteBill(billId);
        assertTrue("Bill should be deleted successfully", deleted);

        Bill deletedBill = billDAO.getBillById(billId);
        assertNull("Deleted bill should be null", deletedBill);
    }
}
