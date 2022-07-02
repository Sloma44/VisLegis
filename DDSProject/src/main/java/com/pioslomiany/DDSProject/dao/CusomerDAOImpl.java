package com.pioslomiany.DDSProject.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.Customer;

@Repository
public class CusomerDAOImpl {

	@Autowired
	private EntityManager entityManager;
	
	public List<Customer> getAll() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
		
		return query.getResultList();
	}

	
	
}
