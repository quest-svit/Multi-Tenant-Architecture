package com.dange.tanmay.service;

import com.dange.tanmay.dao.DepartmentDAO;
import com.dange.tanmay.exception.DuplicateRecordException;
import com.dange.tanmay.exception.RecordNotFoundException;
import com.dange.tanmay.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDAO dao;

    @PersistenceContext
    public EntityManager entityManager;


    public List<Department> getAll(){
        return dao.getAll();
    }

    public Department get(Long id) throws RecordNotFoundException {
        return dao.get(id);
    }

    public void updateDepartment(Department entity) throws RecordNotFoundException{
        dao.update(entity);
    }

    public void deleteDepartment(Long id) throws RecordNotFoundException {
        dao.delete(id);
    }

    public void saveDepartment(Department department) throws DuplicateRecordException {
        dao.create(department);
    }

}
