package com.sl.student.primary.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatasourceConfig {

    public Connection connect(String url, String username, String password) {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected to Database server successfully.");

        return conn;
    }
}

