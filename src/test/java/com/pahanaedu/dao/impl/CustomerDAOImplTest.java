package com.pahanaedu.dao.impl;

import com.pahanaedu.model.Customer;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerDAOImplTest {

    private static CustomerDAOImpl customerDAO;
    private static Customer testCustomer;

    @BeforeClass
    public static void setUpClass() {
        customerDAO = new CustomerDAOImpl();
        testCustomer = new Customer();
        testCustomer.setName("JUnit Test");
        testCustomer.setEmail("junit@example.com");
        testCustomer.setPhone("1234567890");
        testCustomer.setAddress("Test Address");

        customerDAO.addCustomer(testCustomer);
    }

    @AfterClass
    public static void tearDownClass() {
        customerDAO.deleteCustomerByName("JUnit Test");
    }

    @Test
    public void testAddAndGetCustomer() throws SQLException {
        List<Customer> customers = customerDAO.searchCustomers("JUnit Test");
        assertFalse(customers.isEmpty());
        Customer c = customers.get(0);
        assertEquals("JUnit Test", c.getName());
        assertEquals("junit@example.com", c.getEmail());
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        assertTrue(customers.size() > 0);
    }

    @Test
    public void testCustomerCount() {
        int count = customerDAO.getCustomerCount();
        assertTrue(count > 0);
    }

    @Test
    public void testUpdateCustomer() throws SQLException {
        List<Customer> customers = customerDAO.searchCustomers("JUnit Test");
        assertFalse(customers.isEmpty());
        Customer c = customers.get(0);
        c.setPhone("9876543210");
        boolean updated = customerDAO.updateCustomer(c);
        assertTrue(updated);
    }

    @Test
    public void testDeleteCustomer() throws SQLException {
        List<Customer> customers = customerDAO.searchCustomers("JUnit Test");
        assertFalse(customers.isEmpty());
        Customer c = customers.get(0);
        boolean deleted = customerDAO.deleteCustomer(c.getId());
        assertTrue(deleted);
    }
}
