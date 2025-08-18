package com.pahanaedu.service.impl;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.dao.impl.BillDAOImpl;
import com.pahanaedu.dao.impl.CustomerDAOImpl;
import com.pahanaedu.dao.impl.ItemDAOImpl;
import com.pahanaedu.dto.BillDTO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.BillService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {
    BillDAO billDAO;
    CustomerDAO customerDAO;
    ItemDAO itemDAO;
    private Connection connection;

    public BillServiceImpl() {
        this.billDAO = new BillDAOImpl();
        this.customerDAO = new CustomerDAOImpl();
        this.itemDAO = new ItemDAOImpl();
    }


    @Override
    public int createBill(BillDTO billDTO) {
        try {
            Bill bill = new Bill();
            bill.setItemIds(billDTO.getItemIds());
            bill.setCustomerId(billDTO.getCustomerId());
            bill.setTotalAmount(billDTO.getTotalAmount());

            return billDAO.createBill(bill);
        } catch (SQLException e) {
            System.err.println("Error creating bill: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public BillDTO getBillById(int billId) {
        try {
            Bill bill = billDAO.getBillById(billId);
            if (bill == null) return null;

            BillDTO billDTO = new BillDTO();
            billDTO.setBillId(bill.getBillId());
            billDTO.setItemIds(bill.getItemIds());
            billDTO.setCustomerId(bill.getCustomerId());
            billDTO.setBillDateTime(bill.getBillDateTime());
            billDTO.setTotalAmount(bill.getTotalAmount());

            // Get customer name
            Customer customer = customerDAO.getCustomerById(bill.getCustomerId());
            if (customer != null) {
                billDTO.setCustomerName(customer.getName());
            }

            return billDTO;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BillDTO> getAllBills() {
        try {
            List<Bill> bills = billDAO.getAllBills();
            List<BillDTO> billDTOs = new ArrayList<>();

            for (Bill bill : bills) {
                BillDTO billDTO = new BillDTO();
                billDTO.setBillId(bill.getBillId());
                billDTO.setItemIds(bill.getItemIds());
                billDTO.setCustomerId(bill.getCustomerId());
                billDTO.setBillDateTime(bill.getBillDateTime());
                billDTO.setTotalAmount(bill.getTotalAmount());

                // Get customer name
                Customer customer = customerDAO.getCustomerById(bill.getCustomerId());
                if (customer != null) {
                    billDTO.setCustomerName(customer.getName());
                }

                billDTOs.add(billDTO);
            }

            return billDTOs;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean updateBill(BillDTO billDTO) {
        try {
            Bill bill = new Bill();
            bill.setBillId(billDTO.getBillId());
            bill.setItemIds(billDTO.getItemIds());
            bill.setCustomerId(billDTO.getCustomerId());
            bill.setTotalAmount(billDTO.getTotalAmount());

            return billDAO.updateBill(bill);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBill(int billId) {
        try {
            return billDAO.deleteBill(billId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}