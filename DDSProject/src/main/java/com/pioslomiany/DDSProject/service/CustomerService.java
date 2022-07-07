package com.pioslomiany.DDSProject.service;

import java.util.List;

import com.pioslomiany.DDSProject.entity.Letter;
import com.pioslomiany.DDSProject.entity.CourtHearing;
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
	
	public List<LawCase> getAllLawCases();

	public List<LawCase> getCustomerLawCases(Customer theCustomer);

	public void saveLawCase(Customer theCustomer, LawCase theLawCase);

	public void deleteLawCaseById(int theId);

	public LawCase getLawCaseById(int theId);

	public List<Letter> getLetters();

	public void saveLetter(LawCase theLawCase, Letter theLetter);

	public Letter getLetterById(int letterId);

	public void deleteLetterById(int letterId);

	public void saveCourtHearing(LawCase theLawCase, CourtHearing theCourtHearing);

	public CourtHearing getHearingById(int hearingId);

	public void deleteHearingById(int hearingId);
	
}
