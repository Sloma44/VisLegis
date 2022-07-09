package com.pioslomiany.DDSProject.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.CustomerCaseCost;
import com.pioslomiany.DDSProject.entity.LawCase;

@Repository
public class CustomerCaseCostDAOImpl {
	
	@Autowired
	EntityManager entityManager;
	
//	provide a full list of customers costs for the specific LawCase sorted by date
	public List<CustomerCaseCost> getAllCustomerCaseCosts(LawCase theLawCase) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<CustomerCaseCost> query = session.createQuery("FROM CustomerCaseCost c WHERE c.lawCase = :lawCase ORDER BY c.costDate", CustomerCaseCost.class);
		query.setParameter("lawCase", theLawCase);
		
		return query.getResultList();
	}
	
	public CustomerCaseCost getCustomerCaseCostById(int customerCaseCostId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(CustomerCaseCost.class, customerCaseCostId);
	}
	
	public void saveCustomerCaseCost(LawCase theLawCase, CustomerCaseCost theCustomerCaseCost) {
		Session session = entityManager.unwrap(Session.class);
		
		theCustomerCaseCost.setLawCase(theLawCase);
		
		session.saveOrUpdate(theCustomerCaseCost);
	}

	public void deleteCustomerCaseCostById(int customerCaseCostId) {
		Session session = entityManager.unwrap(Session.class);
		
		CustomerCaseCost theCustomerCaseCost = session.get(CustomerCaseCost.class, customerCaseCostId);
		
		session.delete(theCustomerCaseCost);
	}
}
