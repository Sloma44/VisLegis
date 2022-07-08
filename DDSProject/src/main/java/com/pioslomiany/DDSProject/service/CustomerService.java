package com.pioslomiany.DDSProject.service;

import java.util.List;

import com.pioslomiany.DDSProject.entity.CaseIncome;
import com.pioslomiany.DDSProject.entity.CourtHearing;
import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.CustomerCaseCost;
import com.pioslomiany.DDSProject.entity.CustomerContactInfo;
import com.pioslomiany.DDSProject.entity.LawCase;
import com.pioslomiany.DDSProject.entity.Letter;

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

	public List<Letter> getLetters(LawCase theLawCase);

	public void saveLetter(LawCase theLawCase, Letter theLetter);

	public Letter getLetterById(int letterId);

	public void deleteLetterById(int letterId);

	public void saveCourtHearing(LawCase theLawCase, CourtHearing theCourtHearing);

	public CourtHearing getHearingById(int hearingId);

	public void deleteHearingById(int hearingId);

	public void saveCaseIncome(LawCase theLawCase, CaseIncome theCaseIncome);

	public CaseIncome getCaseIncomeById(int incomeId);

	public void deleteCaseIncomeById(int incomeId);

	public List<CaseIncome> getAllCaseIncomes(LawCase theLawCase);
	
	public List<CustomerCaseCost> getAllCustomerCaseCosts(LawCase theLawCase);
	
	public void saveCustomerCaseCost(LawCase theLawCase, CustomerCaseCost theCustomerCaseCost);

	public CustomerCaseCost getCustomerCaseCostById(int customerCaseCostId);

	public void deleteCustomerCaseCostById(int customerCaseCostId);

	public List<CourtHearing> getAllCaseCourtHearing(LawCase theLawCase);
	
}
