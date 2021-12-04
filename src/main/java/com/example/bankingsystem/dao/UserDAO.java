package com.example.bankingsystem.dao;
import com.example.bankingsystem.model.Account;
import com.example.bankingsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.bankingsystem.util.DBUtil;
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
            System.out.println("While searching an user with " + id + " id, an error occurred: " + e);
            throw e;
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        User user = null;

        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setBirthday(rs.getDate("birthday"));
            user.setCurrency(rs.getString("currency"));

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
            user.setUsername(rs.getString("username"));
            user.setBirthday(rs.getDate("birthday"));
            user.setCurrency(rs.getString("currency"));

            Account account = accountDAO.find(rs.getInt("account_id"));
            user.setAccount(account);

            users.add(user);
        }

        return users;
    }

    public void update(Integer id, User user) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                "   UPDATE tbl_user\n" +
                "      SET username = '" + user.getUsername() + "'\n" +
                "          email = '" + user.getEmail() + "'\n" +
                "          password = '" + user.getPassword() + "'\n" +
                "          birthday = '" + user.getBirthday().toString() + "'\n" +
                "          currency = '" + user.getCurrency() + "'\n" +
                "   WHERE EMPLOYEE_ID = " + id + ";\n" +
                "   COMMIT;\n" +
                "END;";

        try {
            User toUpdate = find(id);
            Account account = accountDAO.find(toUpdate.getId());
            DBUtil.dbExecuteUpdate(updateStmt);
            accountDAO.update(account.getId(), user.getAccount());
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

    public void create(User user) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                    "INSERT INTO tbl_user(\n" +
                        "username,\n" +
                        "email,\n" +
                        "password,\n" +
                        "birthday,\n" +
                        "currency)\n" +
                    "VALUES(\n" +
                        "'" + user.getUsername() + "',\n" +
                        "'" + user.getEmail() + "',\n" +
                        "'" + user.getPassword() + "',\n" +
                        "'" + user.getBirthday().toString() + "',\n" +
                        "'" + user.getCurrency() + "',)\n" +
                "END;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
            accountDAO.create(user.getAccount());
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}