package com.example.bankingsystem.controller;

import com.example.bankingsystem.model.User;
import com.example.bankingsystem.service.UserService;
import com.example.bankingsystem.service.impl.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class SignUpController extends Controller {
    // Email format
    private static final String EMAIL_REGEX = "^([a-z0-9!#$&-`.,_]{1,20})@([a-z0-9!#$&-`.,_]{1,20})\\.com$";

    // At least 1 uppercase, 1 special, 3 digits and at least 8 characters long
    private static final String PASSWORD_REGEX = "^(?=.{8,}$)(?=.*[A-Z])(?=.*[0-9]){3,}(?=.*\\W).*$";
    private static final UserService userService;
    private static final Logger logger;

    static {
        userService = new UserServiceImpl();
        logger = Logger.getLogger(String.valueOf(SignUpController.class));
    }

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private DatePicker birthdayField;

    public void signUp(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        LocalDate birthday = birthdayField.getValue();

        if (email.matches(EMAIL_REGEX) && password.matches(PASSWORD_REGEX)) {
            try {
                User user = new User(email, password, birthday, username);
                user = userService.create(user);
                mainScene(event, user);
            } catch (Exception e) {
                logger.severe("Something went wrong\n");
            }
        }
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(String emailField) {
        this.emailField.setText(emailField);
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(String usernameField) {
        this.usernameField.setText(usernameField);
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
}
