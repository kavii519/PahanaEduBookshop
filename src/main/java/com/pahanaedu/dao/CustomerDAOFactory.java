package com.pahanaedu.dao;

import com.pahanaedu.dao.impl.CustomerDAOImpl;

public class CustomerDAOFactory {
    public static CustomerDAO getCustomerDAO() {
        // Here the factory **uses** the implementation
        return new CustomerDAOImpl();
    }
}
