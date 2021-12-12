package com.example.bankingsystem.controller;

import com.example.bankingsystem.Application;
import com.example.bankingsystem.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Controller {
    @FXML
    private static TextField emailField;

    @FXML
    private static TextField usernameField;

    @FXML
    private static TextField passwordField;

    @FXML
    private static DatePicker birthdayField;

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

    static void initializeNodes(User user) {
        emailField.setText(user.getEmail());
        usernameField.setText(user.getEmail());
        birthdayField.setValue(user.getBirthday().toLocalDate());
    }


}
