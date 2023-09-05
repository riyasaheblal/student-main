package com.sl.student.secondary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sl.student.primary.model.DbParams;
import com.sl.student.primary.model.Request;
import com.sl.student.secondary.dao.CommonDaoImpl;
import com.sl.student.secondary.dao.JpaDaoImpl;
import com.sl.student.secondary.util.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class DbService {

    @Autowired
    private CommonDaoImpl commonDaoImpl;

    @Autowired
    private JpaDaoImpl jpaDaoImpl;

    public String getData(Request request) throws SQLException, JsonProcessingException {
        return request.ConnectionString().trim()!=null ? commonDaoImpl.getProcData(request)
                : jpaDaoImpl.getProcData(request);
    }

    private boolean checkProvidedConnectionString(Request request){
        return request.ConnectionString().trim()!=null
                && validateConnectionString(request.ConnectionString()) ? true : false;
    }

    private boolean validateConnectionString(String connectionString){
        DbParams dbParams = CommonUtility.getDbParam(connectionString);
        return dbParams.getDbName()!=null && dbParams.getPort()!=null && dbParams.getUsername()!=null
                && dbParams.getPassword()!=null && dbParams.getServerHost()!=null ? true : false;
    }

}
