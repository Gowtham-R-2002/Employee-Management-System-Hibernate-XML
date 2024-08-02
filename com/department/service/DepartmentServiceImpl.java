package com.department.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.department.dao.DepartmentDao;
import com.department.dao.DepartmentDaoImpl;
import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;

public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDaoImpl();
    
    @Override 
    public void addDepartment(String name) throws EmployeeException {
        departmentDao.addDepartment(name);
    }

    @Override
    public Department getDepartment(int id) throws EmployeeException {
        return departmentDao.getDepartment(id);
    }

    @Override
    public Map<Integer, Department> getDepartments() throws EmployeeException {
        return departmentDao.getDepartments();
    }

    @Override
    public void updateDepartment(int departmentId, String name) throws EmployeeException {
        departmentDao.updateDepartment(departmentId, name);
    }

    @Override
    public void deleteDepartment(int departmentId) throws EmployeeException {
        departmentDao.deleteDepartment(departmentId);
    }

    @Override
    public Set<Employee> getDepartmentEmployees(int id) throws EmployeeException {
        return departmentDao.getDepartmentEmployees(id);
    }
}

