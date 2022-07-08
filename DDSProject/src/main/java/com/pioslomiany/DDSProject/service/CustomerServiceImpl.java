package com.pioslomiany.DDSProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.dao.CaseIncomeDAOImpl;
import com.pioslomiany.DDSProject.dao.CourtHearingDAOImpl;
import com.pioslomiany.DDSProject.dao.CustomerCaseCostDAOImpl;
import com.pioslomiany.DDSProject.dao.CustomerContactInfoDAOImpl;
import com.pioslomiany.DDSProject.dao.CustomerDAOImpl;
import com.pioslomiany.DDSProject.dao.LawCaseDAOImpl;
import com.pioslomiany.DDSProject.dao.LetterDAOImpl;
import com.pioslomiany.DDSProject.entity.CaseIncome;
import com.pioslomiany.DDSProject.entity.CourtHearing;
import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.CustomerCaseCost;
import com.pioslomiany.DDSProject.entity.CustomerContactInfo;
import com.pioslomiany.DDSProject.entity.LawCase;
import com.pioslomiany.DDSProject.entity.Letter;

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
	
	@Autowired
	private CourtHearingDAOImpl courtHearingDAOImpl;
	
	@Autowired
	private CaseIncomeDAOImpl caseIncomeDAOImpl;
	
	@Autowired
	private CustomerCaseCostDAOImpl customerCaseCostDAOImpl;
	
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
	public List<Letter> getLetters(LawCase theLawCase) {
		return letterDAOImpl.getLetters(theLawCase);
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

	@Override
	@Transactional
	public void deleteLetterById(int letterId) {
		letterDAOImpl.deleteLetter(letterId);
	}

	@Override
	@Transactional
		public void saveCourtHearing(LawCase theLawCase, CourtHearing theCourtHearing) {
		courtHearingDAOImpl.saveCourtHearing(theLawCase, theCourtHearing);		
	}
	
	@Override
	@Transactional
	public List<CourtHearing> getAllCaseCourtHearing(LawCase theLawCase) {
		return courtHearingDAOImpl.getAllCaseCourtHearing(theLawCase);
	}

	@Override
	@Transactional
	public CourtHearing getHearingById(int hearingId) {
		return courtHearingDAOImpl.getHearingById(hearingId);
	}

	@Override
	@Transactional
	public void deleteHearingById(int hearingId) {
		courtHearingDAOImpl.deleteHearingById(hearingId);	
	}

	@Override
	@Transactional
	public void saveCaseIncome(LawCase theLawCase, CaseIncome theCaseIncome) {
		caseIncomeDAOImpl.saveCaseIncome(theLawCase, theCaseIncome);
	}

	@Override
	@Transactional
	public CaseIncome getCaseIncomeById(int incomeId) {
		return caseIncomeDAOImpl.getCaseIncomeById(incomeId);
	}

	@Override
	@Transactional
	public void deleteCaseIncomeById(int incomeId) {
		caseIncomeDAOImpl.deleteCaseIncomeById(incomeId);
	}

	@Override
	@Transactional
	public List<CaseIncome> getAllCaseIncomes(LawCase theLawCase) {
		return caseIncomeDAOImpl.getAllCaseIncomes(theLawCase);
	}

	@Override
	@Transactional
	public List<CustomerCaseCost> getAllCustomerCaseCosts(LawCase theLawCase) {
		return customerCaseCostDAOImpl.getAllCustomerCaseCosts(theLawCase);
	}

	@Override
	@Transactional
	public void saveCustomerCaseCost(LawCase theLawCase, CustomerCaseCost theCustomerCaseCost) {
		customerCaseCostDAOImpl.saveCustomerCaseCost(theLawCase, theCustomerCaseCost);
	}

	@Override
	@Transactional
	public CustomerCaseCost getCustomerCaseCostById(int customerCaseCostId) {
		return customerCaseCostDAOImpl.getCustomerCaseCostById(customerCaseCostId);
	}

	@Override
	@Transactional
	public void deleteCustomerCaseCostById(int customerCaseCostId) {
		customerCaseCostDAOImpl.deleteCustomerCaseCostById(customerCaseCostId);
	}
}
