package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.dao.AccountDAO;
import com.example.bankingsystem.model.Account;
import com.example.bankingsystem.service.AccountService;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService {
    private static final AccountDAO accountDAO;
    private static final Logger logger = Logger.getLogger(String.valueOf(AccountService.class));

    static {
        accountDAO = new AccountDAO();
    }

    @Override
    public Account create(Account account) throws SQLException, ClassNotFoundException {
        return accountDAO.create(account);
    }

    @Override
    public Account find(Integer id) throws SQLException, ClassNotFoundException {
        Account account = accountDAO.find(id);
        return account;
    }

    @Override
    public ObservableList<Account> findAll() throws SQLException, ClassNotFoundException {
        ObservableList<Account> accounts = accountDAO.findAll();
        return accounts;
    }

    @Override
    public void update(Integer id, Account account) throws SQLException, ClassNotFoundException {
        accountDAO.update(id, account);
    }

    @Override
    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        accountDAO.delete(id);
    }
}
