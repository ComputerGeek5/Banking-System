package com.example.bankingsystem.dao;
import com.example.bankingsystem.model.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.bankingsystem.util.DBUtil;
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

    private Account getAccountFromResultSet (ResultSet rs) throws SQLException
    {
        Account account = null;

        if (rs.next()) {
            account = new Account();
            account.setId(rs.getInt("id"));
            account.setUserId(rs.getInt("user_id"));
            account.setBalance(rs.getDouble("balance"));
            account.setDate(rs.getDate("date"));
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
            account.setUserId(rs.getInt("user_id"));
            account.setBalance(rs.getDouble("balance"));
            account.setDate(rs.getDate("date"));
            accounts.add(account);
        }

        return accounts;
    }

    public void update(Integer id, Account account) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE tbl_account\n" +
                        "      SET balance = '" + account.getBalance() + "'\n" +
                        "          date = '" + account.getDate() + "'\n" +
                        "   WHERE EMPLOYEE_ID = " + id + ";\n" +
                        "   COMMIT;\n" +
                        "END;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM tbl_account\n" +
                        "         WHERE id ="+ id +";\n" +
                        "   COMMIT;\n" +
                        "END;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public void create(Account account) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO tbl_account(\n" +
                        "user_id,\n" +
                        "balance,\n" +
                        "date,\n" +
                        "VALUES(\n" +
                        "'" + account.getUserId() + "',\n" +
                        "'" + account.getBalance() + "',\n" +
                        "'" + account.getDate() + "',\n" +
                        "END;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}