package com.pahanaedu.dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserDTOTest {

    @Test
    public void testUserDTOGettersAndSetters() {
        UserDTO dto = new UserDTO();
        dto.setUsername("user1");
        dto.setPassword("pass1");

        assertEquals("user1", dto.getUsername());
        assertEquals("pass1", dto.getPassword());
    }

    @Test
    public void testUserDTOConstructor() {
        UserDTO dto = new UserDTO("user2", "pass2");
        assertEquals("user2", dto.getUsername());
        assertEquals("pass2", dto.getPassword());
    }
}
