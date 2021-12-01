package com.example.bankingsystem.model;
import javafx.beans.property.*;

import java.sql.Date;

public class User {
    private IntegerProperty id;
    private StringProperty username;
    private StringProperty email;
    private StringProperty password;
    private SimpleObjectProperty<Date> birthday;
    private StringProperty currency;
    private Account account;

    public User() {}

    public User(IntegerProperty id, StringProperty username, StringProperty email,
                StringProperty password, SimpleObjectProperty<Date> birthday,
                StringProperty currency, Account account) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.currency = currency;
        this.account = account;
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

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public Date getBirthday() {
        return birthday.get();
    }

    public SimpleObjectProperty<Date> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday.set(birthday);
    }

    public String getCurrency() {
        return currency.get();
    }

    public StringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
