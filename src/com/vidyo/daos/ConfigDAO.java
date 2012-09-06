package com.vidyo.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.vidyo.beans.ConfigBean;

public class ConfigDAO implements Serializable{
	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public boolean update(ConfigBean systemConfig){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.merge(systemConfig);
		tr.commit();
		session.close();
		return true;
		
	}
	

	public List getSystenSettings(){
		
		Session session= sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ConfigBean.class);
		criteria.addOrder(Order.asc("id"));
		List<ConfigBean> configList = criteria.list();
		session.close();
		
		if(configList.size()<1){
			return null;
		}
		
		return configList;
	}
	
}
