package com.example.bankingsystem.service;

import com.example.bankingsystem.model.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CRUDService<T> {
    void create(T t) throws SQLException, ClassNotFoundException;

    T find(Integer id) throws SQLException, ClassNotFoundException;

    ObservableList<T> findAll() throws SQLException, ClassNotFoundException;

    void update(Integer id, T t) throws SQLException, ClassNotFoundException;

    void delete(Integer id) throws SQLException, ClassNotFoundException;
}
