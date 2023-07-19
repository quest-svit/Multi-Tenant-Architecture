package com.dange.tanmay.dao;

import com.dange.tanmay.exception.DuplicateRecordException;
import com.dange.tanmay.exception.RecordNotFoundException;
import com.dange.tanmay.model.Department;
import com.dange.tanmay.repository.DepartmentRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Profile("JPA")
@Component
public class DepartmentDAOJPAImpl implements DepartmentDAO {

    @Autowired
    DepartmentRepository repository;

    @Override
    public void create(Department entity) throws DuplicateRecordException {
        Optional<Department> department = repository.findById(entity.getId());
        if (!department.isPresent())
            repository.save(entity);
        else
            throw new DuplicateRecordException("Record already exists for given ID");
    }

    @SneakyThrows
    @Override
    public void update(Department entity) {
        Optional<Department> department = repository.findById(entity.getId());
        if(department.isPresent()){
            Department newEntity = department.get();
            newEntity.setName(entity.getName());
            newEntity.setDescription(entity.getDescription());
            newEntity.setHeadOfDepartment(entity.getHeadOfDepartment());
            repository.save(newEntity);
        } else {
            throw new RecordNotFoundException("No department record exist for given id");
        }
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        Optional<Department> department = repository.findById(id);
        if(department.isPresent()){
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No department record exist for given id");
        }
    }

    @SneakyThrows
    @Override
    public Department get(Long id) {
        Optional<Department> department = repository.findById(id);
        if (department.isPresent())
            return department.get();
        else
            throw new RecordNotFoundException("Record Not Found");
    }

    @Override
    public List<Department> getAll() {
        MDC.put("starttime" , String.valueOf(Instant.now()));
        List<Department> departmentList = repository.findAll();
        MDC.put("endtime" , String.valueOf(Instant.now()));
        return (departmentList.size() > 0) ? departmentList: new ArrayList<Department>();
    }
}
