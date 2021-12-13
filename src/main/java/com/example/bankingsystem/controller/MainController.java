package com.example.bankingsystem.controller;

import com.example.bankingsystem.Application;
import com.example.bankingsystem.model.Account;
import com.example.bankingsystem.model.User;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.UserService;
import com.example.bankingsystem.service.impl.AccountServiceImpl;
import com.example.bankingsystem.service.impl.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Logger;

public class MainController extends Controller {

    // At least 1 uppercase, 1 special, 3 digits and at least 8 characters long
    private static final String PASSWORD_REGEX = "^(?=.{8,}$)(?=.*[A-Z])(?=.*[0-9]){3,}(?=.*\\W).*$";

    private static final UserService userService;
    private static final AccountService accountService;
    private static final Logger logger;

    static {
        userService = new UserServiceImpl();
        accountService = new AccountServiceImpl();
        logger = Logger.getLogger(String.valueOf(SignInController.class));
    }

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private DatePicker birthdayField;

    @FXML
    private TextField depositField;

    @FXML
    private TextField withdrawField;

    @FXML
    private Text balanceField;

    @FXML
    private Text welcomeField;

    private User user;

    private Account account;

    public void homeScene(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Banking System");
        stage.show();
    }

    public void save(ActionEvent event) {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();
            Date birthday = Date.valueOf(birthdayField.getValue());

            user.setUsername(username);

            if (password.matches(PASSWORD_REGEX)) {
                user.setPassword(password);
            }

            user.setBirthday(birthday);
            user = userService.update(user.getId(), user);

            initializeNodes();
        } catch (Exception e) {
            logger.severe("Something went wrong\n");
        }
    }

    public void deposit(ActionEvent event) {
        try {
            BigDecimal balance = account.getBalance();
            BigDecimal deposit = new BigDecimal(depositField.getText().equals("") ? "0" : depositField.getText());
            account.setBalance(balance.add(deposit));
            account = accountService.update(account.getId(), account);

            setBalanceField(account.getBalance().toPlainString());
            setDepositField("");
        } catch (Exception e) {
            logger.severe("Something went wrong\n");
        }
    }

    public void withdraw(ActionEvent event) {
        try {
            BigDecimal balance = account.getBalance();
            BigDecimal withdraw = new BigDecimal(withdrawField.getText().equals("") ? "0" : withdrawField.getText());
            account.setBalance(balance.add(withdraw));
            account = accountService.update(account.getId(), account);

            setBalanceField(account.getBalance().toPlainString());
            setWithdrawField("");
        } catch (Exception e) {
            logger.severe("Something went wrong\n");
        }
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(String usernameField) {
        this.usernameField.setText(usernameField);
    }

    public TextField getDepositField() {
        return depositField;
    }

    public void setDepositField(String depositField) {
        this.depositField.setText(depositField);
    }

    public TextField setWithdrawField() {
        return withdrawField;
    }

    public void setWithdrawField(String withdrawField) {
        this.withdrawField.setText(withdrawField);
    }

    public Text getBalanceField() {
        return balanceField;
    }

    public void setBalanceField(String balanceField) {
        this.balanceField.setText(balanceField);
    }

    public Text getWelcomeField() {
        return welcomeField;
    }

    public void setWelcomeField(String welcomeField) {
        this.welcomeField.setText(welcomeField);
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(String passwordField) {
        this.passwordField.setText(passwordField);
    }

    public DatePicker getBirthdayField() {
        return birthdayField;
    }

    public void setBirthdayField(LocalDate birthdayField) {
        this.birthdayField.setValue(birthdayField);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void initializeNodes() {
        setPasswordField("");
        setUsernameField(user.getUsername());
        setBirthdayField(user.getBirthday().toLocalDate());
        setWelcomeField("Welcome " + user.getUsername());
        setBalanceField(account.getBalance().toPlainString());
    }
}
