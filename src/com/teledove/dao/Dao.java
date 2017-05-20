package com.teledove.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.teledove.model.User;

public class Dao {
	

	
    public User login(String username,String password){
    	String hql = "from User where username=? and password=?";
    	HibernateUtil.getSession().getTransaction().begin();
    	Query query = HibernateUtil.getSession().createQuery(hql);
    	
    	query.setParameter(0, username);
    	query.setParameter(1, password);
		User user = null;
		List<User> userList = query.list();
		if(userList!=null && userList.size()>0){
			user=userList.get(0);
		}
		HibernateUtil.getSession().getTransaction().commit();
		return user;
    }
}