package com.example.bankingsystem.dao;
import com.example.bankingsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.bankingsystem.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
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

    private User getUserFromResultSet(ResultSet rs) throws SQLException
    {
        User user = null;

        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setBirthday(rs.getDate("birthday"));
            user.setCurrency(rs.getString("currency"));
//            user.setAccount()
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
            DBUtil.dbExecuteUpdate(updateStmt);
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
            DBUtil.dbExecuteUpdate(updateStmt);
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
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}