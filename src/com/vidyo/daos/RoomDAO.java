package com.vidyo.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.vidyo.beans.Device;
import com.vidyo.beans.Participant;
import com.vidyo.beans.User;
import com.vidyo.dtos.RoomEntityDTO;

public class RoomDAO implements Serializable{
	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public boolean update(RoomEntityDTO meetingRoom){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.merge(meetingRoom);
		tr.commit();
		session.close();
		return true;
		
	}
	
	public RoomEntityDTO getRoomByUrlKey(String key){

		
		Session session= sessionFactory.openSession();
		Criteria criteria = session.createCriteria(RoomEntityDTO.class);
		criteria.add(Restrictions.ilike("roomUrl", key, MatchMode.ANYWHERE));
		List<RoomEntityDTO> roomList = criteria.list();
		session.close();
		
		if(roomList.size()>0){
			return roomList.get(0);
		}else{
			return null;
		}
	}
	
	public void insertParticipant(Participant meetingParticipant){
		
		Session session= sessionFactory.openSession();
		Transaction tr= session.beginTransaction();
		session.save(meetingParticipant);
		tr.commit();
		session.close();
	}
	
	

	public List getDeviceList(){
		
		Session session= sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Device.class);
		List<Device> deviceList = criteria.list();
		session.close();
		
		if(deviceList.size()<1){
			return null;
		}
		
		return deviceList;
	}
	
}
