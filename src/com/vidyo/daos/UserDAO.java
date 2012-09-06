package com.vidyo.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import com.vidyo.beans.User;
import com.vidyo.dtos.RoomEntityDTO;

public class UserDAO {

	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void insertUser(User user){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.save(user);
		tr.commit();
		session.close();
	}
	
	public void insertRoom(RoomEntityDTO room){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.save(room);
		tr.commit();
		session.close();
	}
	
	public void updateUser(User user){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.update(user);
		tr.commit();
		session.close();
	}
	
	public void deleteUser(User user){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.delete(user);
		tr.commit();
		session.close();
	}
	
	public User getUser(int id){
		
		Session session= sessionFactory.openSession();
		User user = (User)session.get(User.class, id);
		session.close();
		
		return user;
	}
	

	public List getUserListByName(String queryName){
		
		Session session= sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		Criterion  firstnamecr = Restrictions.ilike("firstname", queryName, MatchMode.ANYWHERE);
		Criterion  lastbnamecr = Restrictions.ilike("lastname", queryName, MatchMode.ANYWHERE);		
		Criterion  usernamecr = Restrictions.ilike("username", queryName, MatchMode.ANYWHERE);		
		Criterion  emailaddresscr = Restrictions.ilike("emailaddress", queryName, MatchMode.ANYWHERE);
		Criterion  phonenumbercr = Restrictions.ilike("companyname", queryName, MatchMode.ANYWHERE);
		Criterion  companynamecr = Restrictions.ilike("phonenumber", queryName, MatchMode.ANYWHERE);		
	
		criteria.add(Restrictions.and(Restrictions.eq("userrole", "USER") ,Restrictions.or(Restrictions.or(Restrictions.or(firstnamecr,lastbnamecr), usernamecr), Restrictions.or(Restrictions.or(emailaddresscr,phonenumbercr), companynamecr))));
		List<User> userList = criteria.list();
		session.close();
		
		return userList;
	}
	
	public List getUserListByNameOrEmail(String queryName){
		
		Session session= sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		Criterion  firstnamecr = Restrictions.ilike("firstname", queryName, MatchMode.ANYWHERE);
		Criterion  lastbnamecr = Restrictions.ilike("lastname", queryName, MatchMode.ANYWHERE);		
		Criterion  usernamecr = Restrictions.ilike("username", queryName, MatchMode.ANYWHERE);		
		Criterion  emailaddresscr = Restrictions.ilike("emailaddress", queryName, MatchMode.ANYWHERE);
		
		LogicalExpression con1=	Restrictions.or(firstnamecr,lastbnamecr);
		LogicalExpression con2=	Restrictions.or(usernamecr,emailaddresscr);
		
		criteria.add(Restrictions.or(con1, con2));
		
		List<User> userList = criteria.list();
		session.close();
		
		return userList;
	}

	
	public List getUserByCriteria(String propName, String value){
		Session session= sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq(propName, value).ignoreCase());
		List<User> userList = criteria.list();
		session.close();
		
		if(userList.size()<1){
			return null;
		}
		
		return userList;
	}
}
