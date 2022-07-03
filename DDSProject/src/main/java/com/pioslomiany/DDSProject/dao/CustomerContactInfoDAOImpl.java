package com.pioslomiany.DDSProject.dao;

import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.CustomerContactInfo;

@Repository
public class CustomerContactInfoDAOImpl {
	
	public CustomerContactInfo getCustomerInfo(Customer theCustomer) {
		
		CustomerContactInfo customerInfo = theCustomer.getCustomerContactInfo();
		
		return customerInfo;
	}

}
