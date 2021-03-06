package com.example.bankingsystem.dao;

import com.example.bankingsystem.model.Account;
import com.example.bankingsystem.model.User;
import com.example.bankingsystem.util.CryptoUtil;
import com.example.bankingsystem.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final AccountDAO accountDAO = new AccountDAO();

    public User find(Integer id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM tbl_user WHERE id=" + id;

        try {
            ResultSet result = DBUtil.dbExecuteQuery(selectStmt);
            User user = getUserFromResultSet(result);
            return user;
        } catch (SQLException e) {
            System.out.println("While searching an user with id: " + id + ", an error occurred: " + e);
            throw e;
        }
    }

    public User findTopById() throws SQLException, ClassNotFoundException {
        String findUser =
                "SELECT\n" +
                    "MAX(id) as id\n" +
                "FROM tbl_user\n" +
                "GROUP BY id;";

        Integer id = null;
        User user = null;

        try {
            ResultSet resultSet = DBUtil.dbExecuteQuery(findUser);

            if(resultSet.next()) {
                id = resultSet.getInt("id");
                user = find(id);
            }

            return user;
        } catch (SQLException e) {
            System.out.println("While searching an user with id: " + id + ", an error occurred: " + e);
            throw e;
        }
    }

    public User findByEmail(String email) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM tbl_user WHERE email='" + email + "';";

        try {
            ResultSet result = DBUtil.dbExecuteQuery(selectStmt);
            User user = getUserFromResultSet(result);
            return user;
        } catch (SQLException e) {
            System.out.println("While searching an user with email: " + email + ", an error occurred: " + e);
            throw e;
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        User user = null;

        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setBirthday(rs.getDate("birthday"));

            Account account = accountDAO.find(rs.getInt("account_id"));
            user.setAccount(account);
        }

        return user;
    }

    public ObservableList<User> findAll() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM tbl_user";

        try {
            ResultSet result = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<User> users = getUserList(result);
            return users;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private ObservableList<User> getUserList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<User> users = FXCollections.observableArrayList();

        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
            user.setBirthday(rs.getDate("birthday"));

            Account account = accountDAO.find(rs.getInt("account_id"));
            user.setAccount(account);

            users.add(user);
        }

        return users;
    }


    public User update(int id, User user) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Hash Password
        user.setPassword(CryptoUtil.hash(user.getPassword()));

        String updateStmt =
                "   UPDATE tbl_user\n" +
                "      SET username = '" + user.getUsername() + "',\n" +
                "          email = '" + user.getEmail() + "',\n" +
                "          password = '" + user.getPassword() + "',\n" +
                "          birthday = '" + user.getBirthday().toString() + "'\n" +
                "   WHERE id = " + id + ";\n";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
            User updated = find(id);
            return updated;
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }


    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                "   DELETE FROM tbl_user\n" +
                "         WHERE id ="+ id +";\n" +
                "   COMMIT;\n" +
                "END;";

        try {
            User user = find(id);
            DBUtil.dbExecuteUpdate(updateStmt);
            accountDAO.delete(user.getAccount().getId());
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }


    public User create(User user) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            // Hash Password
            user.setPassword(CryptoUtil.hash(user.getPassword()));

            // Create Account
            Account account = new Account();
            account = accountDAO.create(account);

            String updateStmt =
                    "INSERT INTO tbl_user(\n" +
                        "account_id,\n" +
                        "username,\n" +
                        "email,\n" +
                        "password,\n" +
                        "birthday)\n" +
                    "VALUES(\n" +
                        account.getId() + ",\n" +
                        "'" + user.getUsername() + "',\n" +
                        "'" + user.getEmail() + "',\n" +
                        "'" + user.getPassword() + "',\n" +
                        "'" + user.getBirthday().toString() + "');";

            DBUtil.dbExecuteUpdate(updateStmt);
            return findTopById();
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
}