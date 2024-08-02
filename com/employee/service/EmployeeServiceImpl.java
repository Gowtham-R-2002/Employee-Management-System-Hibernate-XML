package com.employee.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {
    DepartmentService departmentService = new DepartmentServiceImpl();
    EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public void addEmployee(String name, int departmentId,
                            LocalDate dateOfBirth, long phoneNumber,
                            String city) throws EmployeeException {
        Department department = departmentService.getDepartment(departmentId);
        try {
            Employee employee = Employee.Builder.newInstance()
                                                .setName(name)
                                                .setDepartment(department)
                                                .setDateOfBirth(dateOfBirth)
                                                .setPhoneNumber(phoneNumber)
                                                .setCity(city)
                                                .build(); 
            employeeDao.createEmployee(employee);   
        } catch (EmployeeException e) {
             throw new EmployeeException("Error while creating employee with name \n" + name, e);           
        }
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeException {
        if (isEmployeePresent(id)) {
            return employeeDao.fetchEmployeeById(id);
        } else { 
            return null;
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeException {
        employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(int id) throws EmployeeException {
        employeeDao.removeEmployee(id);
    }

    @Override
    public List<Employee> getEmployees() throws EmployeeException {
        return employeeDao.fetchEmployees();
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeException {
        return employeeDao.fetchAllEmployees();
    }

    private boolean isEmployeePresent(int id) throws EmployeeException {
        boolean isPresent = false;
        List<Employee> employees = getEmployees();
        for (Employee employee: employees) {
            if (employee.getId() == id) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }
}