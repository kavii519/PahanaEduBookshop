package com.pahanaedu.service;

import com.pahanaedu.dto.UserDTO;
import com.pahanaedu.model.User;

public interface UserService {
    User authenticate(UserDTO userDTO);
}