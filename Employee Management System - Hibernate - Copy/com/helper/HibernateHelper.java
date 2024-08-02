package com.helper;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.exceptions.EmployeeException;

public class HibernateHelper {
    private static SessionFactory factory = null; 

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("Failed to create sessionFactory object."); 
        }
    }

    public static SessionFactory getFactory() { 
       return factory;
    }  

    public static void closeFactory() throws EmployeeException {
        try {
            factory.close();
        } catch (HibernateException e) {
            throw new EmployeeException("Can't close Factory !", e);
        }
    }
}