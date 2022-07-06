package com.pioslomiany.DDSProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.dao.LetterDAOImpl;
import com.pioslomiany.DDSProject.dao.CustomerContactInfoDAOImpl;
import com.pioslomiany.DDSProject.dao.CustomerDAOImpl;
import com.pioslomiany.DDSProject.dao.LawCaseDAOImpl;
import com.pioslomiany.DDSProject.entity.Letter;
import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.CustomerContactInfo;
import com.pioslomiany.DDSProject.entity.LawCase;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAOImpl customerDAO;
	
	@Autowired
	private CustomerContactInfoDAOImpl customerContactInfoDAOImpl;
	
	@Autowired
	private LawCaseDAOImpl lawCaseDAOImpl;
	
	@Autowired
	private LetterDAOImpl letterDAOImpl;
	
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
	public CustomerContactInfo getCustomerInfo(Customer theCustomer) {
		return customerContactInfoDAOImpl.getCustomerInfo(theCustomer);
	}

	@Override
	@Transactional
	public void saveCustomerContactInfo(Customer theCustomer, CustomerContactInfo theCustomerContactInfo) {
		customerContactInfoDAOImpl.saveCustomerContactInfo(theCustomer, theCustomerContactInfo);
	}

	@Override
	@Transactional
	public List<LawCase> getAllLawCases() {
		return lawCaseDAOImpl.getAllLawCases();
	}

	@Override
	@Transactional
	public List<LawCase> getCustomerLawCases(Customer theCustomer) {
		return lawCaseDAOImpl.getCustomerLawCases(theCustomer);
	}

	@Override
	@Transactional
	public void saveLawCase(Customer theCustomer, LawCase theLawCase) {
		lawCaseDAOImpl.saveLawCase(theCustomer, theLawCase);
	}

	@Override
	@Transactional
	public void deleteLawCaseById(int theId) {
		lawCaseDAOImpl.deleteLawCaseById(theId);
		
	}

	@Override
	@Transactional
	public LawCase getLawCaseById(int theId) {
		return lawCaseDAOImpl.getLawCaseById(theId);
	}

	@Override
	@Transactional
	public List<Letter> getLetters() {
		return letterDAOImpl.getLetters();
	}

	@Override
	@Transactional
	public void saveLetter(LawCase theLawCase, Letter theLetter) {
		letterDAOImpl.saveLetter(theLawCase, theLetter);
	}

	@Override
	@Transactional
	public Letter getLetterById(int letterId) {
		return letterDAOImpl.getLetterById(letterId);
	}
	
	
	
	
	
}
