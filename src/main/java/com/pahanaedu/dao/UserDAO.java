package com.pahanaedu.dao;

import com.pahanaedu.model.User;

public interface UserDAO {
    User findByUsername(String username);
    boolean validateUser(String username, String password);
}