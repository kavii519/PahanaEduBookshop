package com.pahanaedu.service.impl;

import com.pahanaedu.dto.UserDTO;
import com.pahanaedu.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserServiceImplTest {

    private final UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void testAuthenticateValidUser() {
        UserDTO dto = new UserDTO("admin", "admin123");
        User user = userService.authenticate(dto);
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    public void testAuthenticateInvalidUser() {
        UserDTO dto = new UserDTO("admin", "wrongpass");
        User user = userService.authenticate(dto);
        assertNull(user);
    }

    @Test
    public void testAuthenticateNullUserDTO() {
        User user = userService.authenticate(null);
        assertNull(user);
    }
}
