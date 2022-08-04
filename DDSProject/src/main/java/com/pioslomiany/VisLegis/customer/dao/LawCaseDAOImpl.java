package com.pioslomiany.VisLegis.customer.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.customer.entity.Customer;
import com.pioslomiany.VisLegis.customer.entity.LawCase;

@Repository
public class LawCaseDAOImpl {

	@Autowired
	private EntityManager entityManager;
	
	public List<LawCase> getAllLawCases() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<LawCase> query = session.createQuery("FROM LawCase", LawCase.class);
		
		return query.getResultList();
	}
	
	public List<LawCase> getCustomerLawCases(Customer theCustomer) {
		return theCustomer.getLawCases();
	}
	
	public LawCase getLawCaseById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(LawCase.class, theId);
	}
	
	public void saveLawCase(Customer theCustomer, LawCase lawCase) {
		Session session = entityManager.unwrap(Session.class);
		
		lawCase.setCustomer(theCustomer);
		
		session.saveOrUpdate(lawCase);
	}

	public void deleteLawCaseById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		LawCase lawCase = session.get(LawCase.class, theId);
		
		session.delete(lawCase);
	}
}
