package com.dange.tanmay.controllers;


import java.util.List;

import com.dange.tanmay.exception.DuplicateRecordException;
import com.dange.tanmay.exception.RecordNotFoundException;
import com.dange.tanmay.service.DeparmentService;
import org.h2.jdbc.JdbcSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dange.tanmay.model.*;


@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DeparmentService service;

    @RequestMapping(method = RequestMethod.POST, value="/register")
    @ResponseBody
    public ResponseEntity<Reply> registerStudent(@RequestBody Department department) throws Exception {
        try {
            service.saveDepartment(department);
        } catch (DuplicateRecordException e) {
            log.error(String.format("Error occurred while registration. Reason %s", e.getMessage()));
            return getReplyResponseEntity("Register", "Failed");
        } catch (Exception e){
            log.error(String.format("Error occurred while registration. Reason %s", e.getMessage()));
            return getReplyResponseEntity("Register", "Failed");
        }
        return getReplyResponseEntity("Register", "Successful");
    }


    @RequestMapping(method = RequestMethod.GET, value="/allDepartment")
    @ResponseBody
    public ResponseEntity<List<Department>> getAllDepartment() {
        return new ResponseEntity<List<Department>>(service.getAll(),
                new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value="/update")
    @ResponseBody
    public ResponseEntity<Reply> updateDepartment(@RequestBody Department department){
        try {
            service.updateDepartment(department);
        } catch (RecordNotFoundException e) {
            log.error("Error occurred while record update");
            return getReplyResponseEntity("Update", "Failed");
        }
        return getReplyResponseEntity("Update", "Successful");
    }

    @RequestMapping(method = RequestMethod.GET, value="/delete/{id}")
    public ResponseEntity<Reply> deleteDepartment(@PathVariable Long id)  {
        try {
            service.deleteDepartment(id);
        } catch (RecordNotFoundException e) {
            log.error("Error occurred while record delete");
            return getReplyResponseEntity("Delete", "Failed");
        }
        return getReplyResponseEntity("Delete", "Successful");

    }

    @RequestMapping(method = RequestMethod.GET, value="/get/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) throws RecordNotFoundException {
        return new ResponseEntity<Department>(service.get(id),
                new HttpHeaders(), HttpStatus.OK);
    }

    private ResponseEntity<Reply> getReplyResponseEntity(String action, String status) {
        return new ResponseEntity<Reply>(new Reply(action, status),
                new HttpHeaders(), HttpStatus.OK);
    }
}
