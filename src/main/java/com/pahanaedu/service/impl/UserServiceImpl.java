package com.pahanaedu.service.impl;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.dao.impl.UserDAOImpl;
import com.pahanaedu.dto.UserDTO;
import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public User authenticate(UserDTO userDTO) {
        if (userDTO == null || userDTO.getUsername() == null || userDTO.getPassword() == null) {
            return null;
        }

        if (userDAO.validateUser(userDTO.getUsername(), userDTO.getPassword())) {
            return userDAO.findByUsername(userDTO.getUsername());
        }
        return null;
    }
}