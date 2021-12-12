package com.example.bankingsystem.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Account {
    private IntegerProperty id;
    private DoubleProperty balance;
    private SimpleObjectProperty<Timestamp> timestamp;

    public Account() {
        this.balance = new SimpleDoubleProperty(0);
        this.timestamp = new SimpleObjectProperty<>(Timestamp.valueOf(LocalDateTime.now()));
    }

    public Account(double balance, LocalDateTime timestamp) {
        this.balance = new SimpleDoubleProperty(balance);
        this.timestamp = new SimpleObjectProperty<>(Timestamp.valueOf(timestamp));
    }

    public Integer getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
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

    public Timestamp getTimestamp() {
        return timestamp.get();
    }

    public SimpleObjectProperty<Timestamp> timestampProperty() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp.set(timestamp);
    }
}
