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
	private CustomerContactInfoDAOImpl customerContactInfoDAO;
	
	@Autowired
	private LawCaseDAOImpl lawCaseDAO;
	
	@Autowired
	private LetterDAOImpl letterDAO;
	
	@Autowired
	private CourtHearingDAOImpl courtHearingDAO;
	
	@Autowired
	private CaseIncomeDAOImpl caseIncomeDAO;
	
	@Autowired
	private CustomerCaseCostDAOImpl customerCaseCostDAO;
	
	
//	Customer entity
	@Override
	@Transactional
	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}
	
	@Override
	@Transactional
	public Customer getCustomerById(int theId) {
		return customerDAO.getCustomerById(theId);
	}
	
	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public void deleteCustomerById(int theId) {
		customerDAO.deleteCustomerById(theId);
	}
	
	
//	CustomerContactInfo entity
	@Override
	public CustomerContactInfo getCustomerInfo(Customer theCustomer) {
		return customerContactInfoDAO.getCustomerInfo(theCustomer);
	}

	@Override
	@Transactional
	public void saveCustomerContactInfo(Customer theCustomer, CustomerContactInfo theCustomerContactInfo) {
		customerContactInfoDAO.saveCustomerContactInfo(theCustomer, theCustomerContactInfo);
	}

	
//	LawCase entity
	@Override
	@Transactional
	public List<LawCase> getAllLawCases() {
		return lawCaseDAO.getAllLawCases();
	}

	@Override
	@Transactional
	public List<LawCase> getCustomerLawCases(Customer theCustomer) {
		return lawCaseDAO.getCustomerLawCases(theCustomer);
	}

	@Override
	@Transactional
	public LawCase getLawCaseById(int theId) {
		return lawCaseDAO.getLawCaseById(theId);
	}
	
	@Override
	public void saveLawCase(Customer theCustomer, LawCase theLawCase) {
		lawCaseDAO.saveLawCase(theCustomer, theLawCase);
	}

	@Override
	@Transactional
	public void deleteLawCaseById(int theId) {
		lawCaseDAO.deleteLawCaseById(theId);
		
	}


//	Letter entity
	@Override
	@Transactional
	public List<Letter> getLawCaseLetters(LawCase theLawCase) {
		return letterDAO.getLawCaseLetters(theLawCase);
	}

	@Override
	@Transactional
	public Letter getLetterById(int letterId) {
		return letterDAO.getLetterById(letterId);
	}

	@Override
	@Transactional
	public void saveLetter(Letter theLetter) {
		letterDAO.saveLetter(theLetter);
	}

	@Override
	@Transactional
	public void deleteLetterById(int letterId) {
		letterDAO.deleteLetter(letterId);
	}

	
//	CourtHearing entity
	@Override
	@Transactional
	public List<CourtHearing> getAllCaseCourtHearings(LawCase theLawCase) {
		return courtHearingDAO.getAllCaseCourtHearing(theLawCase);
	}
	
	@Override
	@Transactional
	public CourtHearing getHearingById(int hearingId) {
		return courtHearingDAO.getHearingById(hearingId);
	}
	
	@Override
	@Transactional
		public void saveCourtHearing(LawCase theLawCase, CourtHearing theCourtHearing) {
		courtHearingDAO.saveCourtHearing(theLawCase, theCourtHearing);		
	}
	
	
	@Override
	@Transactional
	public void saveCourtHearing(CourtHearing theCourtHearing) {
		courtHearingDAO.saveCourtHearing(theCourtHearing);			
	}

	@Override
	@Transactional
	public void deleteHearingById(int hearingId) {
		courtHearingDAO.deleteHearingById(hearingId);	
	}

	
//	CaseIncome entity
	@Override
	@Transactional
	public List<CaseIncome> getAllCaseIncomes(LawCase theLawCase) {
		return caseIncomeDAO.getAllCaseIncomes(theLawCase);
	}
	
	@Override
	@Transactional
	public CaseIncome getCaseIncomeById(int incomeId) {
		return caseIncomeDAO.getCaseIncomeById(incomeId);
	}
	
	@Override
	@Transactional
	public void saveCaseIncome(CaseIncome theCaseIncome) {
		caseIncomeDAO.saveCaseIncome(theCaseIncome);
	}
	
	

	@Override
	@Transactional
	public void saveCaseIncome(LawCase theLawCase, CaseIncome theCaseIncome) {
		caseIncomeDAO.saveCaseIncome(theLawCase, theCaseIncome);
		
	}

	@Override
	@Transactional
	public void deleteCaseIncomeById(int incomeId) {
		caseIncomeDAO.deleteCaseIncomeById(incomeId);
	}


//	CustomerCaseCost entity
	@Override
	@Transactional
	public List<CustomerCaseCost> getAllCustomerCaseCosts(LawCase theLawCase) {
		return customerCaseCostDAO.getAllCustomerCaseCosts(theLawCase);
	}

	@Override
	@Transactional
	public CustomerCaseCost getCustomerCaseCostById(int customerCaseCostId) {
		return customerCaseCostDAO.getCustomerCaseCostById(customerCaseCostId);
	}
	
	@Override
	@Transactional
	public void saveCustomerCaseCost(LawCase theLawCase, CustomerCaseCost theCustomerCaseCost) {
		customerCaseCostDAO.saveCustomerCaseCost(theLawCase, theCustomerCaseCost);
	}

	@Override
	@Transactional
	public void deleteCustomerCaseCostById(int customerCaseCostId) {
		customerCaseCostDAO.deleteCustomerCaseCostById(customerCaseCostId);
	}
}
