package com.pioslomiany.DDSProject.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.LawCase;

@Repository
public class LawCaseDAOImpl {

	@Autowired
	private EntityManager entityManager;
	
	public List<LawCase> getCustomerLawCases(Customer theCustomer) {
		return theCustomer.getLawCases();
	}
	
	public void saveLawCase(Customer theCustomer, LawCase lawCase) {
		Session session = entityManager.unwrap(Session.class);
		
		lawCase.setCustomer(theCustomer);
		
		session.saveOrUpdate(lawCase);
	}
	
}
