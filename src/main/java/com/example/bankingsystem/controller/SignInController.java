package com.example.bankingsystem.controller;

import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.UserService;
import com.example.bankingsystem.service.impl.AccountServiceImpl;
import com.example.bankingsystem.service.impl.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;

public class SignInController extends Controller {
    private UserService userService = new UserServiceImpl();
    private AccountService accountService = new AccountServiceImpl();

    public void signIn(ActionEvent event) {

    }
}
