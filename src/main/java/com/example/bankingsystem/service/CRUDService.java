package com.example.bankingsystem.service;

import com.example.bankingsystem.model.User;
import javafx.collections.ObservableList;

public interface CRUDService<T> {
    void create(T t);

    T find(Integer id);

    ObservableList<T> findAll();

    void update(Integer id, T t);

    void delete(Integer id);
}
