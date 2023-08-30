package com.sl.student.secondary.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.student.primary.model.Request;
import com.sl.student.secondary.service.DbService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommonDaoImpl implements CommonDao {


    @Autowired
    DbService dbService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String getProcData(Request request) throws JsonProcessingException, SQLException {

        Connection conn = dbService.getDbConnection(request);
        CallableStatement callableStatement = conn.prepareCall("{call " + request.procedureName() + "}");
        ResultSet rs = callableStatement.executeQuery();
        List<Map<String, Object>> listMap =  extractData(rs);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(listMap);
    }

    private static List<Map<String, Object>> extractData(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                row.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
            }
            result.add(row);
        }
        System.out.println(result);
        return result;
    }
}
