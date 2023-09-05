package com.sl.student.primary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sl.student.primary.model.Request;
import com.sl.student.secondary.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    DbService dbService;

    @PostMapping(value = "/getStudents")
    public String getStudent(@RequestBody Request request) throws JsonProcessingException, SQLException {

        return  dbService.getData(request);
    }
}
