package com.example.bankingsystem.model;
import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

public class User {
    private IntegerProperty id;
    private StringProperty email;
    private StringProperty password;
    private StringProperty username;
    private SimpleObjectProperty<Date> birthday;
    private Account account;

    public User() {
        this.email = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.username = new SimpleStringProperty();
        this.birthday = new SimpleObjectProperty<>();
    }

    public User(String email, String password,
                LocalDate birthday, String username) {
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.username = new SimpleStringProperty(username);
        this.birthday = new SimpleObjectProperty<>(Date.valueOf(birthday));
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
