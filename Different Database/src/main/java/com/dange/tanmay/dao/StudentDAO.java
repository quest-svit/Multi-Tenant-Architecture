package com.dange.tanmay.dao;

import java.util.List;
import java.util.Optional;

import com.dange.tanmay.exception.DuplicateRecordException;
import com.dange.tanmay.exception.RecordNotFoundException;
import com.dange.tanmay.model.Student;

public interface StudentDAO {

    public Student get(long id) throws RecordNotFoundException;

    public void save(Student student) throws DuplicateRecordException, RecordNotFoundException;

    public void update(Student student) throws RecordNotFoundException;

    public void delete(long id) throws RecordNotFoundException;

    public List<Student> getAllStudentList();

    public List<Student> getStudentByName(String name1);

    public List<Student> getStudentByAge(int age);

    public List<Student> getStudentByRegistrationNumber(String regNum);

}
