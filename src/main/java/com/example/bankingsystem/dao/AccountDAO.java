package com.example.bankingsystem.dao;

import com.example.bankingsystem.model.Account;
import com.example.bankingsystem.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    public Account find(Integer id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM tbl_account WHERE id=" + id;

        try {
            ResultSet result = DBUtil.dbExecuteQuery(selectStmt);
            Account account = getAccountFromResultSet(result);
            return account;
        } catch (SQLException e) {
            System.out.println("While searching an account with " + id + " id, an error occurred: " + e);
            throw e;
        }
    }

    public Account findTopById() throws SQLException, ClassNotFoundException {
        String selectStmt =
                "SELECT\n" +
                    "MAX(id) as id\n" +
                "FROM tbl_account\n" +
                "GROUP BY id;";

        Integer id = null;
        Account account = null;

        try {
            ResultSet result = DBUtil.dbExecuteQuery(selectStmt);

            if (result.next()) {
                id = result.getInt("id");
                account = find(id);
            }

            return account;
        } catch (SQLException e) {
            System.out.println("While searching an account with " + id + " id, an error occurred: " + e);
            throw e;
        }
    }

    private Account getAccountFromResultSet (ResultSet rs) throws SQLException
    {
        Account account = null;

        if (rs.next()) {
            account = new Account();
            account.setId(rs.getInt("id"));
            account.setBalance(rs.getBigDecimal("balance"));
            account.setTimestamp(rs.getTimestamp("datetime"));
        }

        return account;
    }

    public ObservableList<Account> findAll() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM tbl_account";

        try {
            ResultSet result = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Account> accounts = getAccountList(result);
            return accounts;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private ObservableList<Account> getAccountList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Account> accounts = FXCollections.observableArrayList();

        while (rs.next()) {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setBalance(rs.getBigDecimal("balance"));
            account.setTimestamp(rs.getTimestamp("datetime"));
            accounts.add(account);
        }

        return accounts;
    }

    public Account update(int id, Account account) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "UPDATE tbl_account\n" +
                "      SET balance = '" + account.getBalance() + "',\n" +
                "          datetime = '" + account.getTimestamp() + "'\n" +
                "WHERE id = " + id + ";\n";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
            Account updated = find(id);
            return updated;
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "DELETE FROM tbl_account\n" +
                "    WHERE id ="+ id +";\n";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public Account create(Account account) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "INSERT INTO tbl_account(\n" +
                    "balance,\n" +
                    "datetime)\n" +
                "VALUES(\n" +
                    account.getBalance() + ",\n" +
                    "'" + account.getTimestamp() + "');";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
            return findTopById();
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
}