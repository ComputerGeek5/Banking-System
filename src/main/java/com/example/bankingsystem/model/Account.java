package com.example.bankingsystem.model;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Account {
    private IntegerProperty id;
    private DoubleProperty balance;
    private SimpleObjectProperty<Timestamp> timestamp;

    public Account() {
        this.id = new SimpleIntegerProperty();
        this.balance = new SimpleDoubleProperty(0);
        this.timestamp = new SimpleObjectProperty<>(Timestamp.valueOf(LocalDateTime.now()));
    }

    public Account(double balance, LocalDateTime timestamp) {
        this.id = new SimpleIntegerProperty();
        this.balance = new SimpleDoubleProperty(balance);
        this.timestamp = new SimpleObjectProperty<>(Timestamp.valueOf(timestamp));
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
