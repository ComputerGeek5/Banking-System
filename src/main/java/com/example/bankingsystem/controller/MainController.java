package com.example.bankingsystem.controller;

import com.example.bankingsystem.Application;
import com.example.bankingsystem.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainController extends Controller {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private DatePicker birthdayField;

    public void homeScene(ActionEvent event, User user) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Banking System");
        stage.show();
    }

    public void initializeNodes(User user) {
        setEmailField(user.getEmail());
        setUsernameField(user.getUsername());
        setBirthdayField(user.getBirthday().toLocalDate());
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
