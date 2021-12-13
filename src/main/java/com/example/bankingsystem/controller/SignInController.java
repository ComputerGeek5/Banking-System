package com.example.bankingsystem.controller;

import com.example.bankingsystem.model.User;
import com.example.bankingsystem.service.UserService;
import com.example.bankingsystem.service.impl.UserServiceImpl;
import com.example.bankingsystem.util.CryptoUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.logging.Logger;

public class SignInController extends Controller {
    private static final UserService userService;
    private static final Logger logger;

    static {
        userService = new UserServiceImpl();
        logger = Logger.getLogger(String.valueOf(SignInController.class));
    }

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    public void signIn(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            User user = userService.findByEmail(email);

            if (CryptoUtil.check(password, user.getPassword())) {
                mainScene(event, user);
            }
        } catch (Exception e) {
            logger.severe("Something went wrong\n");
        }
    }
}
