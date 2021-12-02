package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.dao.AccountDAO;
import com.example.bankingsystem.model.Account;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.UserService;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;
    private static final Logger logger = Logger.getLogger(String.valueOf(AccountService.class));

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void create(Account account) {
        try {
            accountDAO.create(account);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Account find(Integer id) {
        try {
            Account account = accountDAO.find(id);
            return account;
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
    public ObservableList<Account> findAll() {
        try {
            ObservableList<Account> accounts = accountDAO.findAll();
            return accounts;
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
    public void update(Integer id, Account account) {
        try {
            accountDAO.update(id, account);
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
            accountDAO.delete(id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
    }
}
