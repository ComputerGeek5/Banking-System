package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.dao.AccountDAO;
import com.example.bankingsystem.dao.UserDAO;
import com.example.bankingsystem.model.User;
import com.example.bankingsystem.service.UserService;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final UserDAO userDAO;
    private static final AccountDAO accountDAO;

    static {
        userDAO = new UserDAO();
        accountDAO = new AccountDAO();
    }

    @Override
    public void create(User user) throws SQLException, ClassNotFoundException {
        userDAO.create(user);
    }

    @Override
    public User find(Integer id) throws SQLException, ClassNotFoundException {
        User user = userDAO.find(id);
        return user;
    }

    @Override
    public ObservableList<User> findAll() throws SQLException, ClassNotFoundException {
        ObservableList<User> users = userDAO.findAll();
        return users;
    }

    @Override
    public void update(Integer id, User user) throws SQLException, ClassNotFoundException {
        userDAO.update(id, user);
    }

    @Override
    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        userDAO.delete(id);
    }
}
