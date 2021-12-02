package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.dao.UserDAO;
import com.example.bankingsystem.model.User;
import com.example.bankingsystem.service.UserService;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private static final Logger logger = Logger.getLogger(String.valueOf(UserService.class));

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void create(User user) {
        try {
            userDAO.create(user);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    public User find(Integer id) {
        try {
            User user = userDAO.find(id);
            return user;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
        }

        return null;
    }

    @Override
    public ObservableList<User> findAll() {
        try {
            ObservableList<User> users = userDAO.findAll();
            return users;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Integer id, User user) {
        try {
            userDAO.update(id, user);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            userDAO.delete(id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
    }
}
