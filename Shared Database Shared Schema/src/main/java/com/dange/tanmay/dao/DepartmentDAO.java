package com.dange.tanmay.dao;

import com.dange.tanmay.exception.DuplicateRecordException;
import com.dange.tanmay.model.Department;
import com.dange.tanmay.model.Student;

import java.util.List;

public interface DepartmentDAO {

    public void create(Department department) throws DuplicateRecordException;

    public void update(Department department);

    public void delete(Long id);

    public Department get(Long id);

    public List<Department> getAll();

}
