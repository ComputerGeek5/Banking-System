package com.example.bankingsystem.service;

import com.example.bankingsystem.model.User;

import java.sql.SQLException;

public interface UserService extends CRUDService<User> {
    User findByEmail(String email) throws SQLException, ClassNotFoundException;
}
