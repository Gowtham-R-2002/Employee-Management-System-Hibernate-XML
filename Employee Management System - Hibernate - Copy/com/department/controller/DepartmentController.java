package com.department.controller;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.department.service.DepartmentService;
import com.department.service.DepartmentServiceImpl;
import com.exceptions.EmployeeException;
import com.model.Department;
import com.model.Employee;
import com.util.Validator;

/** 
 * <p>
 * Responsible for providing endpoints for operations on department such as create, read, etc.
 * </p>
 * @author   Gowtham R
 * @version  1.0
 */
public class DepartmentController {
    Scanner scanner = new Scanner(System.in);
    DepartmentService departmentService = new DepartmentServiceImpl();

    /**
     * <p>
     * Gets choice of department service from the user and calls the
     * appropritate methods.
     * </p>
     */
    public void handleChoice() {
        boolean isExited = false;
        System.out.println("\n------------------------\n");
        System.out.println("Department Services...");
        System.out.println("1. Add a department");
        System.out.println("2. Update a department");
        System.out.println("3. Delete a department");
        System.out.println("4. View departments");
        System.out.println("5. Display employees Department wise");
        System.out.println("6. Back");
        int choice = validateAndReturnNumber();
        try {
            switch (choice) {
            case 1:
                createDepartment();
                break;

            case 2:
                updateDepartment();
                break;

            case 3:
                deleteDepartment();
                break;
    
            case 4: 
                displayDepartments();
                break;
  
            case 5:
                validateEmployeesOfDepartment();
                break;

            case 6:
                isExited = true;
                break;

            default:
                System.out.println("Enter valid input !");
            }
        } catch (EmployeeException e) {
            isExited = true;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if(!isExited) {
            handleChoice();
        }
    }        

    /**
     * <p>
     * Displays all available departments.
     * </p>
     * @throws EmployeeException  Happens when error occurs while fetching
     *                            available departments.
     */
    public void displayDepartments() throws EmployeeException {
        if (departmentService.getDepartments().size() == 0) {
            System.out.println("No departments available");
            System.out.println("Add an department to continue");
        } else {
            Map<Integer, Department> departments 
                = departmentService.getDepartments();        
            for (Map.Entry<Integer, Department> entry 
                     : departments.entrySet()) {
                System.out.println(entry.getKey() + " ---> "
                                   + entry.getValue().getName());
            }
        }
    }

    /** 
     * <p> 
     * Displays Employees of specific Department .
     * </p>
     * 
     * @param departmentId  The ID of the Department to be searched
     * @throws EmployeeException  Happens when error occurs while fetching
     *                            a employees of specific department.
     */
    public void displayEmployees(int departmentId) throws EmployeeException {
        Set<Employee> employees = departmentService
                                       .getDepartmentEmployees(departmentId);;
        if (employees.size() != 0) {
            String format = ("%-5s | %-15s | %-20s | %-15s | %-10s |"
                             + " %-50s | %-10s |");
            System.out.format(format, "ID", "Name", "Age", "Ph.No", 
                              "Department", "Certificate(s)", "City");
            System.out.println();
            for(Employee employee : employees) {
                if (employee.getIsDeleted() == false) {
                    employee.displayEmployee();
                }
            }
        } else {
            System.out.println("No employee found !");
        }
    }

    /**
     * <p>
     * Gets Department name and creates a new Department
     * </p>
     * @throws EmployeeException  Happens when error occurs while creating
     *                            a department.
     */
    public void createDepartment() throws EmployeeException {
        scanner.nextLine();
        System.out.println("Enter name of the department :");
        String name = scanner.nextLine();
        departmentService.addDepartment(name);
        System.out.println("Added success!");
    }

    /**
     * Display Employees of the selected Department
     * @throws EmployeeException  Happens when error occurs while fetching
     *                            employees of a specific department detail.
     */
    public void validateEmployeesOfDepartment() throws EmployeeException {
        if (departmentService.getDepartments().size() == 0) {
            System.out.println("No departments available");
            System.out.println("Add an department to continue");
        } else {
            System.out.println("Available Departments :");
            displayDepartments();
            int departmentId = validateAndReturnNumber();
            if (departmentService.getDepartments()
                                 .containsKey(departmentId)) {
                displayEmployees(departmentId);
            } else {
                System.out.println("Enter valid Department !");
            }
        }
    }

    /**
     * <p>
     * Updates the name of the selected department
     * </p> 
     * @throws EmployeeException  Happens when error occurs while fetching
     *                            or updating a specific department detail.
     */
    public void updateDepartment() throws EmployeeException {
        if (departmentService.getDepartments().size() == 0) {
            System.out.println("No Departments found");
        } else {
            System.out.println("---Enter Department ID---");
            int departmentId = validateAndReturnNumber();
            if (departmentService.getDepartments()
                                  .containsKey(departmentId)) {
                System.out.println("Enter the updated department name : ");
                scanner.nextLine();
                String name = scanner.nextLine();
                departmentService.updateDepartment(departmentId, name);
            } else {
                System.out.println("ID not found !");
            }
        }
    }

    /**
     * <p>
     * Soft Deletes the selected department
     * </p> 
     * @throws EmployeeException  Happens when error occurs while fetching
     *                            or deleting a specific department detail.
     */
    public void deleteDepartment() throws EmployeeException {
        if (departmentService.getDepartments().size() == 0) {
            System.out.println("No Departments found");
        } else {
            System.out.println("---Enter Department ID---");
            int departmentId = validateAndReturnNumber();
            if (departmentService.getDepartments()
                                  .containsKey(departmentId)) {
                departmentService.deleteDepartment(departmentId);
                System.out.println("Delete success !");
            } else {
                System.out.println("ID not found !");
            }
        }
    }


        /**
     * Gets number input from the user and validate it
     * 
     * @return  The validated number input after validation
     */
    public int validateAndReturnNumber() {
        boolean isValid = false;
        do {
            try {
                System.out.println("Enter your Choice : ");
                int validNumber = scanner.nextInt();
                isValid = true;
                return validNumber;
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number !");
                scanner.next();
            }
        } while (!isValid);
        return 0;
    }
}