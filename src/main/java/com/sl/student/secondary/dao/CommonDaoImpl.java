package com.sl.student.secondary.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.student.primary.model.Datatype;
import com.sl.student.primary.model.KeyValuePair;
import com.sl.student.primary.model.Request;

import com.sl.student.secondary.dao.service.DbService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;

@Component
public class CommonDaoImpl implements CommonDao {


    @Autowired
    DbService dbService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String getProcData(Request request) throws JsonProcessingException {

        Connection conn = dbService.getDbConnection(request.ConnectionString());

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(request.procedureName());

        List<KeyValuePair> listKeyValuePairs = request.keyValuePairs();

        for (KeyValuePair keyVal : listKeyValuePairs) {
            storedProcedure.registerStoredProcedureParameter(keyVal.key(), Datatype.valueOf(keyVal.dataType().toUpperCase()).getClazz(), ParameterMode.IN);
        }

        for (KeyValuePair keyVal : listKeyValuePairs) {
            storedProcedure.setParameter(keyVal.key(), keyVal.value());
        }

        List<Object> objectList = storedProcedure.getResultList();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(objectList);
    }
}
