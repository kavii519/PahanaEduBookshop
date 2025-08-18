package com.pahanaedu.dao.impl;

import com.pahanaedu.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDAOImplTest {

    private final UserDAOImpl userDAO = new UserDAOImpl();

    @Test
    public void testFindByUsernameExists() {
        User user = userDAO.findByUsername("admin");
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    public void testFindByUsernameNotExists() {
        User user = userDAO.findByUsername("nonexistent");
        assertNull(user);
    }

    @Test
    public void testValidateUserCorrect() {
        boolean valid = userDAO.validateUser("admin", "admin123");
        assertTrue(valid);
    }

    @Test
    public void testValidateUserIncorrect() {
        boolean valid = userDAO.validateUser("admin", "wrongpass");
        assertFalse(valid);
    }
}
