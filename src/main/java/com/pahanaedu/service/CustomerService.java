package com.pahanaedu.service;

import com.pahanaedu.dto.CustomerDTO;
import com.pahanaedu.model.Customer;
import java.util.List;

public interface CustomerService {
    boolean registerCustomer(Customer customer);
    CustomerDTO getCustomerDetails(int id);
    List<CustomerDTO> getAllCustomers();
    boolean updateCustomerDetails(Customer customer);
    boolean removeCustomer(int id);
    int getTotalCustomerCount();
}