package com.pioslomiany.DDSProject.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.CustomerContactInfo;

@Repository
public class CustomerContactInfoDAOImpl {
	
	@Autowired
	EntityManager entityManager;
	
	public CustomerContactInfo getCustomerInfo(Customer theCustomer) {
		
		CustomerContactInfo customerInfo = theCustomer.getCustomerContactInfo();
		
		return customerInfo;
	}

	public void saveCustomerContactInfo(Customer theCustomer, CustomerContactInfo theCustomerContactInfo) {
		Session session = entityManager.unwrap(Session.class);

		theCustomerContactInfo.setCustomer(theCustomer);
		
		session.saveOrUpdate(theCustomerContactInfo);
		
	}

}
