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
	
//	Customer entity
	public List<Customer> getAllCustomers();

	public Customer getCustomerById(int theId);
	
	public void saveCustomer(Customer theCustomer);

	public void deleteCustomerById(int theId);

	
//	CustomerContactInfo entity
	public CustomerContactInfo getCustomerInfo(Customer theCustomer);

	public void saveCustomerContactInfo(Customer theCustomer, CustomerContactInfo theCustomerContactInfo);
	
	
//	LawCase entity
	public List<LawCase> getAllLawCases();

	public List<LawCase> getCustomerLawCases(Customer theCustomer);

	public LawCase getLawCaseById(int theId);

	public void saveLawCase(Customer theCustomer, LawCase theLawCase);

	public void deleteLawCaseById(int theId);

	
//	Letter entity
	public List<Letter> getLawCaseLetters(LawCase theLawCase);

	public Letter getLetterById(int letterId);

	public void saveLetter(Letter theLetter);

	public void deleteLetterById(int letterId);

	
//	CourtHearing entity
	public List<CourtHearing> getAllCaseCourtHearings(LawCase theLawCase);
	
	public CourtHearing getHearingById(int hearingId);
	
	public void saveCourtHearing(LawCase theLawCase, CourtHearing theCourtHearing);
	
	public void saveCourtHearing(CourtHearing theCourtHearing);

	public void deleteHearingById(int hearingId);

	
//	CaseIncome entity
	public List<CaseIncome> getAllCaseIncomes(LawCase theLawCase);
	
	public CaseIncome getCaseIncomeById(int incomeId);
	
	public void saveCaseIncome(CaseIncome theCaseIncome);

	public void saveCaseIncome(LawCase theLawCase, CaseIncome theCaseIncome);

	public void deleteCaseIncomeById(int incomeId);

	
//	CustomerCaseCost entity
	public List<CustomerCaseCost> getAllCustomerCaseCosts(LawCase theLawCase);
	
	public CustomerCaseCost getCustomerCaseCostById(int customerCaseCostId);
	
	public void saveCustomerCaseCost(LawCase theLawCase, CustomerCaseCost theCustomerCaseCost);

	public void deleteCustomerCaseCostById(int customerCaseCostId);



}
