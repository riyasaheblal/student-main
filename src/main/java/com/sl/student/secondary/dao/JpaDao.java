package com.sl.student.secondary.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sl.student.primary.model.Request;

import java.sql.SQLException;

public interface JpaDao {

    public String getProcData(Request request) throws JsonProcessingException, SQLException;

}
