package com.dange.tanmay.dao;

import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.dange.tanmay.exception.DuplicateRecordException;
import com.dange.tanmay.exception.RecordNotFoundException;
import com.dange.tanmay.model.Department;
import com.dange.tanmay.model.Student;
import com.dange.tanmay.repository.DepartmentRepository;
import com.dange.tanmay.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Profile("JPA")
@Slf4j
@Component
public class StudentDAOJPAImpl implements StudentDAO {

	@Autowired
	private StudentRepository repository;

	@Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Student get(long id) throws RecordNotFoundException {
        Optional<Student> student = repository.findById(id);
        if (student.isPresent())
            return student.get();
        else
            throw new RecordNotFoundException("Record Not Found");
    }

    @Override
    public void save(Student entity) throws DuplicateRecordException,RecordNotFoundException {
        Optional<Department> department =  departmentRepository.findById(entity.getDepartmentId());
        try {
            entity.setDepartment(department.get());
            Optional<Student> student=null ;//= repository.findById(entity.getId());
            if (!student.isPresent())
                repository.save(entity);
            else
                throw new DuplicateRecordException("Record already exists for given ID");
        } catch (NoSuchElementException e){
            throw new RecordNotFoundException("Department Record Not Found");
        }
    }

    @Override
    public List<Student> getAllStudentList() {
        log.info("Inside Dao for GetAllStudents");
        List<Student> studentList = repository.findAll();
        return (studentList.size() > 0) ? studentList: new ArrayList<Student>();
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return repository.findAll().stream().filter(s -> s.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        return repository.findAll().stream().filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentByRegistrationNumber(String regNum) {
        return repository.findAll().stream().filter(s -> s.getRegistrationNumber().equals(regNum))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Student entity) throws RecordNotFoundException {
        Optional<Student> student=null;// = repository.findById(entity.getId());
        if(student.isPresent()){
            Student newEntity = student.get();
            newEntity.setName(entity.getName());
            newEntity.setAge(entity.getAge());
            newEntity.setRegistrationNumber(entity.getRegistrationNumber());
            repository.save(newEntity);
        } else {
            throw new RecordNotFoundException("No student record exist for given id");
        }
    }

    @Override
    public void delete(long id) throws RecordNotFoundException {
        Optional<Student> student = repository.findById(id);
        if(student.isPresent()){
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No student record exist for given id");
        }
    }
}
