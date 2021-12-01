package com.example.bankingsystem.model;

import javafx.beans.property.*;

import java.sql.Date;

public class Account {
    private IntegerProperty id;
    private IntegerProperty userId;
    private DoubleProperty balance;
    private SimpleObjectProperty<Date> date;

    public Account() {
    }

    public Account(IntegerProperty id, IntegerProperty userId, DoubleProperty balance, SimpleObjectProperty<Date> date) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.date = date;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public double getBalance() {
        return balance.get();
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }
}
