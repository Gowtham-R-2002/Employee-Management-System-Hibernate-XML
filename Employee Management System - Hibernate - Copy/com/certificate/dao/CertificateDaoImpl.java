package com.certificate.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.exceptions.EmployeeException;
import com.helper.HibernateHelper;
import com.model.Certificate;
import com.model.Department;
import com.model.Employee;

public class CertificateDaoImpl implements CertificateDao {
    @Override
    public void createCertificate(String name) throws EmployeeException {
        Session session = HibernateHelper.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Certificate certificate = new Certificate(name);
            Integer id = (Integer) session.save(certificate);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while adding certificate of name : " + name, e);
        } finally {
            session.close();
        }
    }

    @Override
    public Certificate fetchCertificate(int id) throws EmployeeException {
        Session session = HibernateHelper.getFactory().openSession();
        Transaction transaction = null;
        Certificate certificate = null;
        try {
            transaction = session.beginTransaction();
            Query<Certificate> query = session.createQuery("from Certificate where id = :id AND isDeleted = 0", Certificate.class);
            query.setParameter("id", id);
            certificate = query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while fetching certificate of id : " + id, e);
        } finally {
            session.close();
        }
        return certificate;
    }

    @Override
    public void addEmployee(Certificate certificate, Employee employee) throws EmployeeException {
        Session session = HibernateHelper.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Employee employeeFromDb = session.get(Employee.class, employee.getId());
            Certificate certificateFromDb = session.get(Certificate.class, certificate.getId());
            Set<Certificate> certificates = employeeFromDb.getCertificates();
            Set<Employee> employees = certificateFromDb.getEmployees();
            certificates.add(certificateFromDb);
            employees.add(employeeFromDb);
            session.saveOrUpdate(employeeFromDb);    
            session.saveOrUpdate(certificateFromDb );  
            transaction.commit();          
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while adding certificate " + certificate.getName()
                                        + "to employee id : " + employee.getName(), e);
        } finally {
            session.close();
        }
    }
 
    @Override
    public Map<Integer, Certificate> fetchCertificates() throws EmployeeException {
        Session session = HibernateHelper.getFactory().openSession();
        Map<Integer, Certificate> certificates = new HashMap<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<Certificate> query = session.createQuery("from Certificate where isDeleted = 0", Certificate.class);
            List<Certificate> certificatesFromDb = query.list();
            for (Certificate certificate : certificatesFromDb) {
                certificates.put(certificate.getId(), certificate);
            }
            transaction.commit();     
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while fetching available certificates !", e);
        } finally {
            session.close();
        }
    return certificates;
    }

    @Override
    public void updateCertificate(int certificateId, String name) throws EmployeeException {
        Session session = HibernateHelper.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("UPDATE Certificate SET name = :name WHERE id = :id");
            query.setParameter("id", certificateId);
            query.setParameter("name", name);
            int status = query.executeUpdate();
            if(status == 1) {
                System.out.println("success");
            } else {
                System.out.println("failed");        
            }           
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while updating certificate of id : " + certificateId, e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteCertificate(int certificateId) throws EmployeeException {
        Session session = HibernateHelper.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("UPDATE Certificate SET isDeleted = :isDeleted WHERE id = :certificateId");
            query.setParameter("isDeleted", true);
            query.setParameter("certificateId", certificateId);
            int status = query.executeUpdate();
            if(status == 1) {
                System.out.println("success");
            } else {
                System.out.println("failed");        
            }           
            transaction.commit();            
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while deleting certificate of id : " + certificateId, e);
        } finally {
            session.close();
        }
    }


    /**
     * <p>
     * Fetches Employees from the specific certificate id provided
     * @param id  The Id of the specific certificate
     * @return    The Employees associated with the given certificate id
     * @throw EmployeeException  Exception thrown when fetching all employees of
                                 certificates has some issues.
     */
    public Set<Employee> getCertificateEmployees(int id) throws EmployeeException {
        Set<Employee> employees = new HashSet<>();
        Session session = HibernateHelper.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Certificate certificate = session.get(Certificate.class, id);
            if (certificate != null) {
                employees = certificate.getEmployees();
            }
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while getting employees of certificate of id : " + id, e);
        } finally {
            session.close();
        }
        return employees;
    }
}