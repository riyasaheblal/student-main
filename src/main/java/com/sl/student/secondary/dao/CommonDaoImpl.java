package com.sl.student.secondary.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.student.primary.model.KeyValuePair;
import com.sl.student.primary.model.Request;
import com.sl.student.secondary.service.DbService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NonNull;
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

        CallableStatement callableStatement = conn.prepareCall(createSqlStatement(request));

        List<KeyValuePair> paramKeyValues = request.keyValuePairs();
        if (paramKeyValues!=null && paramKeyValues.size()>0){
            int i = 1;
            for (KeyValuePair keyValuePair: paramKeyValues) {
                callableStatement.setObject(i,keyValuePair.value());
                i++;
            }
        }
        ResultSet rs = callableStatement.executeQuery();
        List<Map<String, Object>> listMap =  extractData(rs);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(listMap);
    }

    public static String createSqlStatement(Request request){

        StringBuilder stringBuilder = new StringBuilder("call ");

        List<KeyValuePair> paramKeyValues = request.keyValuePairs();

        if (paramKeyValues==null || paramKeyValues.size()==0){
            stringBuilder.append(request.procedureName()+"(");
        }else {
            stringBuilder.append(request.procedureName()+"(?");
        }
        if (paramKeyValues!=null && paramKeyValues.size()>1) {
            for (KeyValuePair keyValuePair : paramKeyValues) {
                stringBuilder.append(", ?");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private static List<Map<String, Object>> extractData(@NonNull ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                row.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
            }
            result.add(row);
        }
        return result;
    }
}
