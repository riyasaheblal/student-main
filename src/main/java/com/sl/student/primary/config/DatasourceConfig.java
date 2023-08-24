package com.sl.student.primary.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Log4j2
@Component
public class DatasourceConfig {

    public Connection connect(String url, String username, String password) {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        log.info("Connected to Database server successfully.");
        return conn;
    }
}

