package com.pioslomiany.VisLegis.customer.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.customer.entity.Customer;

@Repository
public class CustomerDAOImpl {

	@Autowired
	private EntityManager entityManager;
	
	public List<Customer> getAllCustomers() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
		
		return query.getResultList();
	}
	
	public Customer getCustomerById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(Customer.class, theId);
	}
	
	public void saveCustomer(Customer theCustomer) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(theCustomer);
	}


	public void deleteCustomerById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		Customer customer = session.get(Customer.class, theId);
		
		session.delete(customer);
	}
}
