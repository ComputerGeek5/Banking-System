package com.example.bankingsystem.model;

import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

public class Account {
    private IntegerProperty id;
    private IntegerProperty userId;
    private DoubleProperty balance;
    private SimpleObjectProperty<Date> date;

    public Account() {}

    public Account(int userId, double balance,
                   LocalDate date) {
        setUserId(userId);
        setBalance(balance);
        setDate(Date.valueOf(date));
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
