package com.employee.service;

import java.time.LocalDate;
import java.util.List;

import com.model.Employee;
import com.exceptions.EmployeeException;

/** 
 * Handles operations on employee such as adding, deleting, etc
 */
public interface EmployeeService {

    /** 
     * Adds an Employee to the repository
     * 
     * @param name          The name of the Employee
     * @param departmentId  The Id of the department
     * @param dateOfBirth   The DOB of the Employee
     * @param phoneNumber   The phone number of Employee
     * @param city          The city of the Employee
     */    
    public void addEmployee(String name, int departmentId,
                            LocalDate dateOfBirth, long phoneNumber,
                            String city) throws EmployeeException;

    /** 
     * Gets a specific Employee from the repository
     * 
     * @param id  The ID of the Employee to be searched,null if not found
     */
    public Employee getEmployeeById(int id) throws EmployeeException;

    /** 
     * Updates an Employee in the repository
     * 
     * @param employee  The Employee containing updated data
     */
    public void updateEmployee(Employee employee) throws EmployeeException;

    /** 
     * Soft deletes an Employee in the repository
     * 
     * @param id  The Id of the Employee to be deleted
     */
    public void deleteEmployee(int id) throws EmployeeException;    

    /** 
     * Gets Employees considering isDeleted value
     * 
     * @return  The Employees data without soft deletion
     */
    public List<Employee> getEmployees() throws EmployeeException;

    /** 
     * Gets Employees without considering isDeleted value
     * 
     * @return  The Employees data regardless of soft deletion
     */
    public List<Employee> getAllEmployees() throws EmployeeException;

}