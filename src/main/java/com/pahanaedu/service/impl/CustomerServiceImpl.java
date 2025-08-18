package com.pahanaedu.service.impl;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.impl.CustomerDAOImpl;
import com.pahanaedu.dto.CustomerDTO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class CustomerServiceImpl implements CustomerService {
    private Connection connection;
    public CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public boolean registerCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    @Override
    public CustomerDTO getCustomerDetails(int id) {
        Customer customer = customerDAO.getCustomerById(id);
        if (customer != null) {
            return convertToDTO(customer);
        }
        return null;
    }

    public abstract boolean addCustomer(Customer customer);

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        List<CustomerDTO> customerDTOs = new ArrayList<>();

        for (Customer customer : customers) {
            customerDTOs.add(convertToDTO(customer));
        }
        return customerDTOs;
    }


    @Override
    public boolean updateCustomerDetails(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    @Override
    public boolean removeCustomer(int id) {
        return customerDAO.deleteCustomer(id);
    }

    @Override
    public int getTotalCustomerCount() {
        return customerDAO.getCustomerCount();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        dto.setCreatedAt(customer.getCreatedAt());
        return dto;
    }
}
