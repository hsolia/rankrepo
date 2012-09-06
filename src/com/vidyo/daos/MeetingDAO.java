package com.vidyo.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.vidyo.beans.Meeting;

public class MeetingDAO {

	private HibernateTemplate hibernateTemplate;
	

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void insertMeeting(Meeting meeting){
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		session.save(meeting);
		tr.commit();
		session.close();
	}
	
	public void updateMeeting(Meeting meeting){
		hibernateTemplate.update(meeting);
	}
	
}
