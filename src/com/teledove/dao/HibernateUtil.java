package com.teledove.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil { 
    private final static SessionFactory sessionFactory=buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
    	Configuration configuration=new Configuration().configure();
    	ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
    			.applySettings(configuration.getProperties()).buildServiceRegistry();
    	return configuration.buildSessionFactory(serviceRegistry); 
	}
    public static Session getSession(){
    	return sessionFactory.getCurrentSession();
    }
}
