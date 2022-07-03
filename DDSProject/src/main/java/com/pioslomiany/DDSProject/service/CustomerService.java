package com.pioslomiany.DDSProject.service;

import java.util.List;

import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.CustomerContactInfo;
import com.pioslomiany.DDSProject.entity.LawCase;

public interface CustomerService {
	
	public List<Customer> getAll();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomerById(int theId);

	public void deleteCustomerById(int theId);

	public CustomerContactInfo getCustomerInfo(Customer theCustomer);

	public void saveCustomerContactInfo(Customer theCustomer, CustomerContactInfo theCustomerContactInfo);

	public List<LawCase> getCustomerLawCases(Customer theCustomer);

	public void saveLawCase(Customer theCustomer, LawCase theLawCase);
	
}
