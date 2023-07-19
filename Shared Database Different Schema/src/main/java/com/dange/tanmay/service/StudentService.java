package com.dange.tanmay.service;

import com.dange.tanmay.dao.StudentDAO;
import com.dange.tanmay.exception.DuplicateRecordException;
import com.dange.tanmay.exception.RecordNotFoundException;
import com.dange.tanmay.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentService {
    
    @Autowired
    StudentDAO dao;

    public List<Student> getAllStudent(){
        log.info("Inside getAllStudents");
        return dao.getAllStudentList();
    }

    public Student getStudentId(Long id) throws RecordNotFoundException {
        return dao.get(id);
    }

    public void updateStudent(Student entity) throws RecordNotFoundException{
        dao.update(entity);
    }

    public void deleteStudent(Long id) throws RecordNotFoundException {
        dao.delete(id);
    }

    public void saveStudent(Student student) throws DuplicateRecordException,RecordNotFoundException {
        dao.save(student);
    }

    public List<Student> getStudentByName(String name){
        return dao.getStudentByName(name);
    }

    public List<Student> getStudentByAge(int age){
        return dao.getStudentByAge(age);
    }

    public List<Student> getStudentByRegistrationNumber(String regNum){
        return dao.getStudentByRegistrationNumber(regNum);
    }

}
