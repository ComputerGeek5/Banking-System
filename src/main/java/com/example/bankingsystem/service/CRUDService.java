package com.example.bankingsystem.service;

import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public interface CRUDService<T> {
    T create(T t) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException;

    T find(Integer id) throws SQLException, ClassNotFoundException;

    ObservableList<T> findAll() throws SQLException, ClassNotFoundException;

    void update(Integer id, T t) throws SQLException, ClassNotFoundException;

    void delete(Integer id) throws SQLException, ClassNotFoundException;
}
