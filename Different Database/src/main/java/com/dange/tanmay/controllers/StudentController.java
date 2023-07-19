package com.dange.tanmay.controllers;

import com.dange.tanmay.exception.DuplicateRecordException;
import com.dange.tanmay.exception.RecordNotFoundException;
import com.dange.tanmay.model.Reply;
import com.dange.tanmay.model.Student;
import com.dange.tanmay.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/student")
public class StudentController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentService service;

	@RequestMapping(method = RequestMethod.POST, value="/register")
	@ResponseBody
	public ResponseEntity<Reply> registerStudent(@RequestBody Student student) throws Exception {
		try {
			service.saveStudent(student);
		} catch (DuplicateRecordException e) {
			log.error("Error occurred while registration. Reason {}", e.getMessage());
			return getStudentReplyResponseEntity("Register", "Failed");
		} catch (RecordNotFoundException e1){
			log.error("No such Department Exists");
			return getStudentReplyResponseEntity("Register", "Failed");
		}
		return getStudentReplyResponseEntity("Register", "Successful");
	}


	@RequestMapping(method = RequestMethod.GET, value="/allStudents")
	@ResponseBody
	public ResponseEntity<List<Student>> getAllStudents(@RequestHeader Map<String, String> headers) {
		MDC.put("user", headers.get("user"));
		log.info("Fetching all Students from database");
		return new ResponseEntity<List<Student>>(service.getAllStudent(),
				new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value="/getStudentByName/{name}")
	@ResponseBody
	public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name) {
		log.info("Fetching {} from database" , name);
		return new ResponseEntity<List<Student>>(service.getStudentByName(name),
				new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value="/getStudentByAge/{age}")
	@ResponseBody
	public List<Student> getStudentByName(@PathVariable int age) {
		log.info("Fetching Students from database with age {}" , age);
		return service.getStudentByAge(age);
	}

	@RequestMapping(method = RequestMethod.GET, value="/getStudentByRegNum/{regNum}")
	@ResponseBody
	public ResponseEntity<List<Student>> getStudentByRegNum(@PathVariable String regNum) {
		return new ResponseEntity<List<Student>>(service.getStudentByRegistrationNumber(regNum),
				new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value="/update")
	@ResponseBody
	public ResponseEntity<Reply> updateStudent(@RequestBody Student student){
		try {
			service.updateStudent(student);
		} catch (RecordNotFoundException e) {
			log.error("Error occurred while record update");
			return getStudentReplyResponseEntity("Update", "Failed");
		}
		return getStudentReplyResponseEntity("Update", "Successful");
	}

	@RequestMapping(method = RequestMethod.GET, value="/delete/{id}")
	public ResponseEntity<Reply> deleteStudent(@PathVariable Long id)  {
		try {
			service.deleteStudent(id);
		} catch (RecordNotFoundException e) {
			log.error("Error occurred while record delete");
			return getStudentReplyResponseEntity("Delete", "Failed");
		}
		return getStudentReplyResponseEntity("Delete", "Successful");

	}

	@RequestMapping(method = RequestMethod.GET, value="/get/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws RecordNotFoundException {
		return new ResponseEntity<Student>(service.getStudentId(id),
				new HttpHeaders(), HttpStatus.OK);
	}

	private ResponseEntity<Reply> getStudentReplyResponseEntity(String action, String status) {
		return new ResponseEntity<Reply>(new Reply(action, status),
				new HttpHeaders(), HttpStatus.OK);
	}
}
