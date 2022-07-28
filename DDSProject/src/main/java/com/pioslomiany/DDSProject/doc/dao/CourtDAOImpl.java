package com.pioslomiany.DDSProject.doc.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.doc.entity.Court;

@Repository
public class CourtDAOImpl {

	@Autowired
	EntityManager entityManager;
	
	public List<Court> getAllCourts() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Court> query = session.createQuery("FROM Court", Court.class);
		
		return query.getResultList();
	}
	
	public Court getCourtById (int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(Court.class, theId);
	}
	
	public void saveCourt (Court theCourt) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(theCourt);
	}
	
	public void deleteCourtById (int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		Court court = session.get(Court.class, theId);
		
		session.delete(court);
	}
	
}
