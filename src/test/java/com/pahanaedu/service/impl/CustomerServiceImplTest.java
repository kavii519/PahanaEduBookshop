package com.pahanaedu.service.impl;

import com.pahanaedu.dto.CustomerDTO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.util.DBConnection;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerServiceImplTest {

    private static Connection connection;
    private CustomerServiceImpl customerService;

    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        connection = DBConnection.getConnection();
    }

    @Before
    public void setUp() throws SQLException {
        customerService = new CustomerServiceImpl() {
            @Override
            public boolean addCustomer(Customer customer) {
                return false;
            }
        };

        // clean test data
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM customers");
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    private Customer createTestCustomer(String name) {
        Customer c = new Customer();
        c.setName(name);
        c.setEmail(name.toLowerCase() + "@example.com");
        c.setPhone("123456789");
        c.setAddress("Test Address");
        c.setCreatedAt(LocalDateTime.now());
        c.setUpdatedAt(LocalDateTime.now());
        return c;
    }

    @Test
    public void testRegisterCustomerAndGetDetails() {
        Customer customer = createTestCustomer("Alice");
        assertTrue(customerService.registerCustomer(customer));

        List<CustomerDTO> all = customerService.getAllCustomers();
        assertEquals(1, all.size());

        CustomerDTO dto = customerService.getCustomerDetails(all.get(0).getId());
        assertNotNull(dto);
        assertEquals("Alice", dto.getName());
    }

    @Test
    public void testGetAllCustomers() {
        customerService.registerCustomer(createTestCustomer("Alice"));
        customerService.registerCustomer(createTestCustomer("Bob"));

        List<CustomerDTO> customers = customerService.getAllCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    public void testUpdateCustomerDetails() {
        Customer customer = createTestCustomer("Charlie");
        customerService.registerCustomer(customer);

        CustomerDTO savedDto = customerService.getAllCustomers().get(0);

        // Need to fetch Customer model to update
        Customer saved = customerService.getCustomerById(savedDto.getId());
        saved.setName("Charlie Updated");

        assertTrue(customerService.updateCustomerDetails(saved));

        Customer updated = customerService.getCustomerById(saved.getId());
        assertEquals("Charlie Updated", updated.getName());
    }

    @Test
    public void testRemoveCustomer() {
        customerService.registerCustomer(createTestCustomer("Dave"));

        CustomerDTO savedDto = customerService.getAllCustomers().get(0);
        assertTrue(customerService.removeCustomer(savedDto.getId()));

        assertNull(customerService.getCustomerById(savedDto.getId()));
    }

    @Test
    public void testGetTotalCustomerCount() {
        assertEquals(0, customerService.getTotalCustomerCount());
        customerService.registerCustomer(createTestCustomer("Eve"));
        assertEquals(1, customerService.getTotalCustomerCount());
    }
}
