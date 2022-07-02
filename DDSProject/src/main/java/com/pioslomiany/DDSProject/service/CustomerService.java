package com.pioslomiany.DDSProject.service;

import java.util.List;

import com.pioslomiany.DDSProject.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getAll();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomerById(int theId);

	public void deleteCustomerById(int theId);
	
}
