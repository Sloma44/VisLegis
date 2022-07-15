package com.pioslomiany.DDSProject.calculator.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.calculator.entity.Values;

@Repository
public class ValuesDAO {

	@Autowired
	EntityManager entityManager;
	
	public double getEntityValueById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(Values.class, theId).getValue();
	}
}
