package com.pioslomiany.VisLegis.calculator.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.calculator.entity.Values;

@Repository
public class ValuesDAO {

	@Autowired
	EntityManager entityManager;
	
	public double getEntityValueById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(Values.class, theId).getValue();
	}
	
	public List<Values> getAllValues() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Values> query = session.createQuery("FROM Values", Values.class);
		
		return query.getResultList();
	}
	
	public Values getValueById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(Values.class, theId);
		
	}
	
	public void modifyValue(Values value) {
		Session session = entityManager.unwrap(Session.class);
		
		session.update(value);
	}
}
