package com.vidyo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.vidyo.beans.Faq;

public class CommonDAO {

	private Logger LOGGER = Logger.getLogger(CommonDAO.class);
	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void insertFaq(Faq faq){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.save(faq);
		tr.commit();
		session.close();
	}
	
	public void updateUser(Faq faq){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.update(faq);
		tr.commit();
		session.close();
	}
	
	public void deleteFaq(Faq faq){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.delete(faq);
		tr.commit();
		session.close();
	}
	

	public List getAllFaq(){
		
		Session session= sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Faq.class);
		List<Faq> faqList = criteria.list();
		session.close();
		
		return faqList;
	}
	
	public Integer getNextRoomNumber(){
		
		Integer nextRooomNumber = 0;
		
		try{
			Connection conn = sessionFactory.openSession().connection();
			CallableStatement call = conn.prepareCall("{call NEXT_SEQUENCE(?, ?)}");
			call.setString(1, "ROOM_NUMBER");
			call.registerOutParameter(2,java.sql.Types.INTEGER);
			call.execute();
			
			nextRooomNumber=call.getInt(2);
			return nextRooomNumber;
		}
		catch(Exception ex){
			LOGGER.error("error in getNextRoomNumber", ex);
		}
		
		return nextRooomNumber;
	}

}
