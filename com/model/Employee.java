package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.model.Certificate;
import com.model.Department;
import com.util.DateUtil;

/** 
 * <p>
 * Represents an employee in an organisation
 * Contains details of employee such as ID, name, DOB, etc.
 * Includes department and collection of certificate(s) done
 * by the employee.
 * </p>
 * @author   Gowtham R
 * @version  1.0  
 */
public class Employee {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private long phoneNumber;
    private Department department;
    private Set<Certificate> certificates;
    private String city;
    private boolean isDeleted;

    public Employee () {}

    public Employee(Builder builder) {
        this.name = builder.name;
        this.dateOfBirth = builder.dateOfBirth;
        this.phoneNumber = builder.phoneNumber;
        this.department = builder.department;
        this.city = builder.city;
        this.isDeleted = false;
        certificates = new HashSet<>();
    }

    public static class Builder {
        private String name;
        private LocalDate dateOfBirth;
        private long phoneNumber;
        private Department department;
        private String city;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {}
    
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setPhoneNumber(long phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setDepartment(Department department) {
            this.department = department;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String calculateAge() {
        return DateUtil.getAge(dateOfBirth);
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public Department getDepartment() {   
        return department;
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public String getCity() {
        return city;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    } 

    public void displayEmployee() {
        StringBuilder certificateString = new StringBuilder();
        for (Certificate certificate : certificates) {
            certificateString.append(certificate.getName() + ",");
        }
        String format = ("%-5s | %-15s | %-20s | %-15s | %-10s |"
                         + " %-50s | %-10s |");
        System.out.format(format, getId(), getName(), calculateAge(),
                          getPhoneNumber(), getDepartment().getName(), 
                          certificateString.length() == 0 
                          ? "No Certificates found"
                          : certificateString,
                          getCity());
        System.out.println();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Employee employee = (Employee) object;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

