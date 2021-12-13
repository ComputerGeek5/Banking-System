package com.example.bankingsystem.service;

import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public interface CRUDService<T> {
    T create(T t) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException;

    T find(int id) throws SQLException, ClassNotFoundException;

    ObservableList<T> findAll() throws SQLException, ClassNotFoundException;

    T update(int id, T t) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException;

    void delete(int id) throws SQLException, ClassNotFoundException;
}
