module com.example.bankingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.sql.rowset;
    requires mysql.connector.java;

    opens com.example.bankingsystem to javafx.fxml;
    exports com.example.bankingsystem;

    opens com.example.bankingsystem.controller to javafx.fxml;
    exports com.example.bankingsystem.controller;
}