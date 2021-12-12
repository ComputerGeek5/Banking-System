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
    private TextField emailFieldMain;

    @FXML
    private TextField usernameFieldMain;

    @FXML
    private DatePicker birthdayFieldMain;

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
        emailFieldMain.setText(user.getEmail());
        usernameFieldMain.setText(user.getEmail());
        birthdayFieldMain.setValue(user.getBirthday().toLocalDate());
    }
}
