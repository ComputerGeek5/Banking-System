package com.example.bankingsystem.controller;

import com.example.bankingsystem.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController extends Controller {
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
}
