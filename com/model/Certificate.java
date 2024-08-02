package com.model;

import java.util.Set;
import java.util.Objects;

import com.model.Employee;

/** 
 * <p>
 * Certificate is a reward or proof for certain acheivement given for a person.
 * Represents a certificate given to an employee that contains name of the certificate 
 * and the collection of related employees in it 
 * </p>
 *
 * @author  Gowtham R
 * @version 1.0
 */
public class Certificate {
    private int id;
    private String name;
    private boolean isDeleted;
    private Set<Employee> employees;

    public Certificate() {}

    public Certificate(String name) {
        this.name = name;
    }

    public Certificate(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Certificate that = (Certificate) object;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}