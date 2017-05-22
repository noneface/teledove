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
    public List<User> load(){
    	String hql ="from User";
    	HibernateUtil.getSession().getTransaction().begin();
    	Query query = HibernateUtil.getSession().createQuery(hql);

		List<User> user = null;
		List<User> ulist = query.list(); 
		if(ulist!=null & ulist.size()>0){
			user= ulist;
		}

		HibernateUtil.getSession().getTransaction().commit();
		
		return user;
    }
    public void add(User user){
    	HibernateUtil.getSession().getTransaction().begin();
    	HibernateUtil.getSession().save(user);
    	HibernateUtil.getSession().getTransaction().commit();
    }
    public User queryUserByUsername(String username){
    	String hql = "from User where username = ?";
    	HibernateUtil.getSession().getTransaction().begin();
    	Query query = HibernateUtil.getSession().createQuery(hql);
    	query.setParameter(0, username);
    	User user = null;
        List<User> Huser = query.list();
		if(Huser!=null && Huser.size()>0){
			user=Huser.get(0);
		}
		HibernateUtil.getSession().getTransaction().commit();
		return user;
    }
}
