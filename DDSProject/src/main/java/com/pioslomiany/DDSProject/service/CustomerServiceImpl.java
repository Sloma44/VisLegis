package com.pioslomiany.DDSProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.dao.CustomerContactInfoDAOImpl;
import com.pioslomiany.DDSProject.dao.CustomerDAOImpl;
import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.CustomerContactInfo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAOImpl customerDAO;
	
	@Autowired
	private CustomerContactInfoDAOImpl customerContactInfoDAOImpl;
	
	@Override
	@Transactional
	public List<Customer> getAll() {
		return customerDAO.getAll();
	}
	
	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomerById(int theId) {
		return customerDAO.getCustomerById(theId);
	}

	@Override
	@Transactional
	public void deleteCustomerById(int theId) {
		customerDAO.deleteCustomerById(theId);
	}
	
	@Override
	@Transactional
	public CustomerContactInfo getCustomerInfo(Customer theCustomer) {
		return customerContactInfoDAOImpl.getCustomerInfo(theCustomer);
	}

	@Override
	@Transactional
	public void saveCustomerContactInfo(Customer theCustomer, CustomerContactInfo theCustomerContactInfo) {
		customerContactInfoDAOImpl.saveCustomerContactInfo(theCustomer, theCustomerContactInfo);
	}
	
	
	
}
