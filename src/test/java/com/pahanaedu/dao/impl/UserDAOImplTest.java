package com.pahanaedu.dao.impl;

import com.pahanaedu.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDAOImplTest {

    private UserDAOImpl userDAO;

    @Before
    public void setUp() {
        userDAO = new UserDAOImpl();
    }

    @Test
    public void testFindByUsername_UserExists() {
        String username = "admin";
        User user = userDAO.findByUsername(username);
        Assert.assertNotNull("User should exist", user);
        Assert.assertEquals("Username should match", username, user.getUsername());
    }

    @Test
    public void testFindByUsername_UserDoesNotExist() {
        String username = "nonexistentuser";
        User user = userDAO.findByUsername(username);
        Assert.assertNull("User should be null", user);
    }

    @Test
    public void testValidateUser_CorrectCredentials() {
        String username = "admin";
        String password = "admin123";
        boolean valid = userDAO.validateUser(username, password);
        Assert.assertTrue("Credentials should be valid", valid);
    }

    @Test
    public void testValidateUser_IncorrectPassword() {
        String username = "admin";
        String password = "wrongpassword";
        boolean valid = userDAO.validateUser(username, password);
        Assert.assertFalse("Password is wrong, should return false", valid);
    }

    @Test
    public void testValidateUser_NonexistentUser() {
        String username = "ghost";
        String password = "any";
        boolean valid = userDAO.validateUser(username, password);
        Assert.assertFalse("User does not exist, should return false", valid);
    }
}
