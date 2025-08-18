package com.pahanaedu.service.impl;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.dto.BillDTO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;

public class BillServiceImplTest {

    private BillServiceImpl billService;
    private FakeBillDAO fakeBillDAO;
    private FakeCustomerDAO fakeCustomerDAO;

    @Before
    public void setUp() {
        fakeBillDAO = new FakeBillDAO();
        fakeCustomerDAO = new FakeCustomerDAO();

        billService = new BillServiceImpl() {
            { // override DAOs with fakes
                this.billDAO = fakeBillDAO;
                this.customerDAO = fakeCustomerDAO;
                this.itemDAO = new FakeItemDAO();
            }
        };
    }

    @Test
    public void testCreateBillSuccess() {
        BillDTO dto = new BillDTO();
        dto.setCustomerId(1);
        dto.setItemIds(Arrays.asList(1, 2).toString());
        dto.setTotalAmount(100.0);

        int id = billService.createBill(dto);
        assertTrue(id > 0);
    }

    @Test
    public void testCreateBillExceptionBranch() {
        fakeBillDAO.throwException = true;
        BillDTO dto = new BillDTO();
        int result = billService.createBill(dto);
        assertEquals(-1, result);
    }

    @Test
    public void testGetBillByIdSuccess() {
        BillDTO dto = billService.getBillById(1);
        assertNotNull(dto);
        assertEquals("Test Customer", dto.getCustomerName());
    }

    @Test
    public void testGetBillByIdNull() {
        BillDTO dto = billService.getBillById(999); // nonexistent
        assertNull(dto);
    }

    @Test
    public void testGetBillByIdExceptionBranch() {
        fakeBillDAO.throwException = true;
        BillDTO dto = billService.getBillById(1);
        assertNull(dto);
    }

    @Test
    public void testGetAllBillsSuccess() {
        List<BillDTO> bills = billService.getAllBills();
        assertEquals(1, bills.size());
        assertEquals("Test Customer", bills.get(0).getCustomerName());
    }

    @Test
    public void testGetAllBillsExceptionBranch() {
        fakeBillDAO.throwException = true;
        List<BillDTO> bills = billService.getAllBills();
        assertNotNull(bills);
        assertTrue(bills.isEmpty());
    }

    @Test
    public void testUpdateBillSuccess() {
        BillDTO dto = new BillDTO();
        dto.setBillId(1);
        dto.setCustomerId(1);
        dto.setItemIds(Arrays.asList(1).toString());
        dto.setTotalAmount(200.0);

        assertTrue(billService.updateBill(dto));
    }

    @Test
    public void testUpdateBillExceptionBranch() {
        fakeBillDAO.throwException = true;
        BillDTO dto = new BillDTO();
        assertFalse(billService.updateBill(dto));
    }

    @Test
    public void testDeleteBillSuccess() {
        assertTrue(billService.deleteBill(1));
    }

    @Test
    public void testDeleteBillExceptionBranch() {
        fakeBillDAO.throwException = true;
        assertFalse(billService.deleteBill(1));
    }

    // -------- Fake DAO implementations --------

    static class FakeBillDAO implements BillDAO {
        boolean throwException = false;

        @Override
        public int createBill(Bill bill) throws SQLException {
            if (throwException) throw new SQLException("Fake exception");
            return 1;
        }

        @Override
        public Bill getBillById(int billId) throws SQLException {
            if (throwException) throw new SQLException("Fake exception");
            if (billId == 1) {
                Bill b = new Bill();
                b.setBillId(1);
                b.setCustomerId(1);
                b.setItemIds(Arrays.asList(1, 2).toString());
                b.setBillDateTime(Timestamp.valueOf(LocalDateTime.now()));
                b.setTotalAmount(150.0);
                return b;
            }
            return null;
        }

        @Override
        public List<Bill> getAllBills() throws SQLException {
            if (throwException) throw new SQLException("Fake exception");
            Bill b = new Bill();
            b.setBillId(1);
            b.setCustomerId(1);
            b.setItemIds(Arrays.asList(1).toString());
            b.setBillDateTime(Timestamp.valueOf(LocalDateTime.now()));
            b.setTotalAmount(99.0);
            return Collections.singletonList(b);
        }

        @Override
        public boolean updateBill(Bill bill) throws SQLException {
            if (throwException) throw new SQLException("Fake exception");
            return true;
        }

        @Override
        public boolean deleteBill(int billId) throws SQLException {
            if (throwException) throw new SQLException("Fake exception");
            return true;
        }
    }

    static class FakeCustomerDAO implements CustomerDAO {
        @Override
        public boolean addCustomer(Customer customer) {
            return false;
        }

        @Override
        public Customer getCustomerById(int id) {
            if (id == 1) {
                Customer c = new Customer();
                c.setId(1);
                c.setName("Test Customer");
                return c;
            }
            return null;
        }

        @Override
        public List<Customer> getAllCustomers() {
            return List.of();
        }

        @Override
        public boolean updateCustomer(Customer customer) {
            return false;
        }

        @Override
        public boolean deleteCustomer(int id) {
            return false;
        }

        @Override
        public int getCustomerCount() {
            return 0;
        }

        @Override
        public int createCustomer(Customer customer) {
            return 0;
        }

        @Override
        public void deleteCustomerByName(String test) {

        }
        // other methods not needed
    }

    static class FakeItemDAO implements ItemDAO {
        @Override
        public boolean addItem(Item item) throws SQLException {
            return false;
        }

        @Override
        public boolean updateItem(Item item) throws SQLException {
            return false;
        }

        @Override
        public boolean deleteItem(int id) throws SQLException {
            return false;
        }

        @Override
        public Item getItemById(int id) throws SQLException {
            return null;
        }

        @Override
        public List<Item> getAllItems() throws SQLException {
            return List.of();
        }
        // Not used in BillServiceImpl
    }
}
