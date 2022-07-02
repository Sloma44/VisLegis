package com.pioslomiany.DDSProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.dao.CustomerDAOImpl;
import com.pioslomiany.DDSProject.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAOImpl customerDAO;
	
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
	
	
	
}
