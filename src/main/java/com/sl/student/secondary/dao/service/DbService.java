package com.sl.student.secondary.dao.service;

import com.sl.student.primary.config.DatasourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class DbService {

    @Autowired
    private DatasourceConfig datasourceConfig;

    public Connection getDbConnection(String connectionString) {
        Connection conn;
        if (url != null || url.trim().length() == 0) {
            conn = datasourceConfig.connect(url, username, password);
        } else {
            //here we will return default connection
        }

        return null;
    }
}
