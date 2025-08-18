package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.CustomerDAOFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDAOFactoryTest {

    @Test
    public void testGetCustomerDAO() {
        CustomerDAO dao = CustomerDAOFactory.getCustomerDAO();
        assertNotNull("Factory should return a DAO instance", dao);
        assertTrue("Returned object should be instance of CustomerDAO",
                dao instanceof CustomerDAO);
    }
}
