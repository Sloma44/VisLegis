package com.pioslomiany.VisLegis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pioslomiany.VisLegis.customer.entity.CaseIncome;
import com.pioslomiany.VisLegis.customer.entity.CourtHearing;
import com.pioslomiany.VisLegis.customer.entity.Customer;
import com.pioslomiany.VisLegis.customer.entity.CustomerCaseCost;
import com.pioslomiany.VisLegis.customer.entity.CustomerContactInfo;
import com.pioslomiany.VisLegis.customer.entity.LawCase;
import com.pioslomiany.VisLegis.customer.entity.Letter;
import com.pioslomiany.VisLegis.customer.service.CustomerService;
import com.pioslomiany.VisLegis.doc.entity.ClauseRequestForm;
import com.pioslomiany.VisLegis.doc.entity.Court;
import com.pioslomiany.VisLegis.doc.entity.JoiningTheCaseForm;
import com.pioslomiany.VisLegis.doc.entity.JustificationRequestForm;
import com.pioslomiany.VisLegis.doc.service.DocGeneratorService;

@Controller
@RequestMapping("/vislegis/customerList")
public class CustomersListController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DocGeneratorService docGeneratorService;
	
	
	/* List of all customers
	 * On this page you can:
	 * 	- read full list of all customer
	 * 	- add new customer (first name, last name and all other customer info),
	 * 	- go to the specific customer details
	 */
	@GetMapping("")
	public String getCustomersList(Model model) {

		List<Customer> customerList = customerService.getAllCustomers();
		
		model.addAttribute("customers", customerList);	
		
		return "customer/list-view";
	}

	
	
	/* Specific customer details
	 * On this page you can:
	 * 	- read all specified customer informations,
	 * 	- read all law cases of specified customer
	 * 	- modify customer (first name, last name)
	 * 	- modify customer informations
	 * 	- delete customer
	 * 	- add new law case,
	 * 	- go to specific law case details
	 */
	@GetMapping("customerDetails")
	public String customerDetails(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		CustomerContactInfo theCustomerContactInfo = customerService.getCustomerInfo(theCustomer);
		List<LawCase> lawCaseList = customerService.getCustomerLawCases(theCustomer);
		
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerContactInfo", theCustomerContactInfo);
		model.addAttribute("lawCases", lawCaseList);
		
		return "customer/customer-details";
	}
	
	
//	Actions (CRUD) on Customer and CustomerContactInfo entities
	@GetMapping("saveCustomerForm")
	public String saveCustomerForm(Model model) {
		
		model.addAttribute("customer", new Customer());
		model.addAttribute("customerContactInfo", new CustomerContactInfo());
		
		return "customer/save-customer-form";
	}
	
	@GetMapping("updateCustomerForm")
	public String updateCustomerForm(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		CustomerContactInfo theCustomerContactInfo = customerService.getCustomerInfo(theCustomer);
		
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerContactInfo", theCustomerContactInfo);

		return "customer/save-customer-form";
	}
	
	@PostMapping("saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult bindingResult,
			@ModelAttribute("customerContactInfo") CustomerContactInfo theCustomerContactInfo,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			return "customer/save-customer-form";
		}
		
		customerService.saveCustomer(theCustomer);
		customerService.saveCustomerContactInfo(theCustomer, theCustomerContactInfo);
		
		int customerId = theCustomer.getId();
		
		return "redirect:/vislegis/customerList/customerDetails?customerId=" + customerId;
	}
	
	@GetMapping("customerDetails/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteCustomerById(theId);
		
		return "redirect:/vislegis/customerList";
	}
	
	

	
	
	/* Specific law case details
	 * On this page you can:
	 * 	- read customer info,
	 * 	- read law case info (of specified customer)
	 * 	- read case: all incomes, correspondence (letters), customer costs and hearing
	 * 	- modify law case
	 * 	- delete law case
	 * 	- add new / modify / delete income for case
	 * 	- add new / modify / delete customer cost for case
	 * 	- add new / modify / delete court hearing for case
	 */
	@GetMapping("customerDetails/caseDetails")
	public String caseDetails(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		List<CaseIncome> caseIncomeList = customerService.getAllCaseIncomes(theLawCase);
		List<Letter> theJournal = customerService.getLawCaseLetters(theLawCase);
		List<CustomerCaseCost> customerCaseCostList = customerService.getAllCustomerCaseCosts(theLawCase);
		List<CourtHearing> courtHearingList = customerService.getAllCaseCourtHearings(theLawCase);
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("caseIncomes", caseIncomeList);
		model.addAttribute("journals", theJournal);
		model.addAttribute("customerCaseCosts", customerCaseCostList);
		model.addAttribute("courtAgenda", courtHearingList);
		
		return "customer/lawCase-details";
	}

	
//	Actions (CRUD) on LawCase entity
	@GetMapping("customerDetails/saveLawCaseForm")
	public String saveLawCaseForm(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		List<Court> courts = docGeneratorService.getAllCourts();
		
		model.addAttribute("courts", courts);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("lawCase", new LawCase());
		
		return "customer/save-lawCase-form";
	}
	
	@GetMapping("customerDetails/updateLawCaseForm")
	public String updateLawCaseForm(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		List<Court> courts = docGeneratorService.getAllCourts();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("courts", courts);
		
		return "customer/save-lawCase-form";
	}
	
	@PostMapping("customerDetails/saveLawCase")
	public String saveLawCase(@ModelAttribute("lawCase") LawCase theLawCase,
								@ModelAttribute("customer") Customer theCustomer,
								Model model) {
		
		customerService.saveLawCase(theCustomer, theLawCase);
		
		int caseId = theLawCase.getCaseId();
		return "redirect:/vislegis/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	@GetMapping("customerDetails/caseDetails/deleteLawCase")
	public String deleteLawCase(@RequestParam("caseId") int theId) {
		
		LawCase lawCase = customerService.getLawCaseById(theId);
		int theCustomerId = lawCase.getCustomer().getId();
		
		customerService.deleteLawCaseById(theId);

		return "redirect:/vislegis/customerList/customerDetails?customerId=" + theCustomerId;
	}
	
	
	
//	Typical CRUD actions on Income, CustomerCaseCost, CourtHearings
	
//	Income
	@GetMapping("customerDetails/caseDetails/saveIncomeForm")
	public String saveIncomeForm(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("income", new CaseIncome());

		return "customer/save-income-form-by-customer";
	}
	
	@GetMapping("customerDetails/caseDetails/updateIncomeForm")
	public String updateIncomeForm(@RequestParam("incomeId") int incomeId, Model model) {
		
		CaseIncome theCaseIncome = customerService.getCaseIncomeById(incomeId);
		LawCase theLawCase = theCaseIncome.getLawCase();		
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("income", theCaseIncome);
		
		return "customer/save-income-form-by-customer";
	}
	
	@PostMapping("customerDetails/caseDetails/saveIncome")
	public String saveIncome(@ModelAttribute("lawCase") LawCase theLawCase,
							@ModelAttribute("income") CaseIncome theCaseIncome, Model model) {
		
		customerService.saveCaseIncome(theLawCase, theCaseIncome);
		
		int caseId = theLawCase.getCaseId();
		
		return "redirect:/vislegis/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	@GetMapping("customerDetails/caseDetails/deleteIncome")
	public String deleteIncome(@RequestParam("incomeId") int incomeId, Model model) {
		
		int caseId = customerService.getCaseIncomeById(incomeId).getLawCase().getCaseId();
		
		customerService.deleteCaseIncomeById(incomeId);
		
		return "redirect:/vislegis/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	
//	CustomerCaseCost
	@GetMapping("customerDetails/caseDetails/saveCustomerCaseCostForm")
	public String saveCustomerCaseCostForm(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerCaseCost", new CustomerCaseCost());

		return "customer/save-customerCaseCost-form-by-customer";
	}
	
	@GetMapping("customerDetails/caseDetails/updateCustomerCaseCostForm")
	public String updateCustomerCaseCostForm(@RequestParam("customerCaseCostId") int customerCaseCostId, Model model) {
		
		CustomerCaseCost theCustomerCaseCost = customerService.getCustomerCaseCostById(customerCaseCostId);
		LawCase theLawCase = theCustomerCaseCost.getLawCase();		
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerCaseCost", theCustomerCaseCost);
		
		return "customer/save-customerCaseCost-form-by-customer";
	}
	
	@PostMapping("customerDetails/caseDetails/saveCustomerCaseCost")
	public String saveCustomerCaseCost(@ModelAttribute("lawCase") LawCase theLawCase,
							@ModelAttribute("customerCaseCost") CustomerCaseCost theCustomerCaseCost, Model model) {
		
		customerService.saveCustomerCaseCost(theLawCase, theCustomerCaseCost);
		
		int caseId = theLawCase.getCaseId();
		
		return "redirect:/vislegis/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	@GetMapping("customerDetails/caseDetails/deleteCustomerCaseCost")
	public String deleteCustomerCaseCost(@RequestParam("customerCaseCostId") int customerCaseCostId, Model model) {
		
		int caseId = customerService.getCustomerCaseCostById(customerCaseCostId).getLawCase().getCaseId();
		
		customerService.deleteCustomerCaseCostById(customerCaseCostId);
		
		return "redirect:/vislegis/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	
//	CourtHearings
	@GetMapping("customerDetails/caseDetails/saveCourtHearingForm")
	public String saveCourtHearingForm(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("courtHearing", new CourtHearing());

		return "customer/save-courtHearing-form-by-customer";
	}
	
	@GetMapping("customerDetails/caseDetails/updateCourtHearingForm")
	public String updateCourtHearingForm(@RequestParam("courtHearingId") int courtHearingId, Model model) {
		
		CourtHearing theCourtHearing = customerService.getHearingById(courtHearingId);
		LawCase theLawCase = theCourtHearing.getLawCase();		
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("courtHearing", theCourtHearing);
		
		return "customer/save-courtHearing-form-by-customer";
	}
	
	@PostMapping("customerDetails/caseDetails/saveCourtHearing")
	public String saveCourtHearing(@ModelAttribute("lawCase") LawCase theLawCase,
							@ModelAttribute("courtHearing") CourtHearing theCourtHearing, Model model) {
		
		customerService.saveCourtHearing(theLawCase, theCourtHearing);
		
		int caseId = theLawCase.getCaseId();
		
		return "redirect:/vislegis/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	@GetMapping("customerDetails/caseDetails/deleteCourtHearing")
	public String deleteCourtHearing(@RequestParam("courtHearingId") int courtHearingId, Model model) {
		
		int caseId = customerService.getHearingById(courtHearingId).getLawCase().getCaseId();
		
		customerService.deleteHearingById(courtHearingId);
		
		return "redirect:/vislegis/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	
//	Document generator from case details page

//	Generates document for "Prokuratura wstąpienie"	for selected case
// Document generator method located in DocGeneratorController (public void createProsecutorAccessionDocx)
	@GetMapping("customerDetails/caseDetails/createProsecutorAccessionDocxForm")
	public String createProsecutorAccessionDocxForm(@RequestParam("caseId") int caseId, Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		LawCase theLawCase = customerService.getLawCaseById(caseId);
		
		// load from LawCase data to the "document generator form"
		int courtId = theLawCase.getCourt().getId();
		String firstName = theLawCase.getCustomer().getFirstName();
		String lastName = theLawCase.getCustomer().getLastName();
		String caseSignature = theLawCase.getSignature();
		
		model.addAttribute("courts", courts);
		model.addAttribute("prosecutorAccession", new JoiningTheCaseForm(courtId, firstName, lastName, caseSignature));
		
		// for button back "<<Powrót". If caseIdContion is not null it will go back to caseDetail page,
		// else it will go back to DocGenerater main menu from DocGeneratorController
		model.addAttribute("caseIdCondition", theLawCase.getCaseId());
		
		return "docGenerator/prosecutorAccession-docx-form";
	}
	
//	Generates document for "Wniosek o uzasadnienie"	for selected case
// Document generator method located in DocGeneratorController (public void createJustificationRequestDocx)
	@GetMapping("customerDetails/caseDetails/createJustificationRequestDocxForm")
	public String createJustificationRequestDocxForm(@RequestParam("caseId") int caseId, Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		LawCase theLawCase = customerService.getLawCaseById(caseId);
		
		// load from LawCase data to the "document generator form"
		int courtId = theLawCase.getCourt().getId();
		String firstName = theLawCase.getCustomer().getFirstName();
		String lastName = theLawCase.getCustomer().getLastName();
		String caseSignature = theLawCase.getSignature();
		
		model.addAttribute("courts", courts);
		model.addAttribute("justificationRequest", new JustificationRequestForm(courtId, firstName, lastName, caseSignature));
		
		// for button back "<<Powrót". If caseIdContion is not null it will go back to caseDetail page,
		// else it will go back to DocGenerater main menu from DocGeneratorController
		model.addAttribute("caseIdCondition", theLawCase.getCaseId());
		
		return "docGenerator/justificationRequest-docx-form";
	}
	
	
//	Generates document for "Wniosek o klauzulę"	for selected case
// Document generator method located in DocGeneratorController (public void createClauseRequestDocx)
	@GetMapping("customerDetails/caseDetails/createClauseRequestDocxForm")
	public String createClauseRequestDocxForm(@RequestParam("caseId") int caseId, Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		LawCase theLawCase = customerService.getLawCaseById(caseId);
		
		// load from LawCase data to the "document generator form"
		int courtId = theLawCase.getCourt().getId();
		String firstName = theLawCase.getCustomer().getFirstName();
		String lastName = theLawCase.getCustomer().getLastName();
		String caseSignature = theLawCase.getSignature();
		
		model.addAttribute("courts", courts);
		model.addAttribute("clauseRequest", new ClauseRequestForm(courtId, firstName, lastName, caseSignature));
		
		// for button back "<<Powrót". If caseIdContion is not null it will go back to caseDetail page,
		// else it will go back to DocGenerater main menu from DocGeneratorController
		model.addAttribute("caseIdCondition", theLawCase.getCaseId());
		
		return "docGenerator/clauseRequest-docx-form";
	}
	
	
//	Generates document for "Wstąpienie do sprawy" for selected case
//	The same form is used as in "prosecutorAccession"
// Document generator method located in DocGeneratorController (public void createJoiningTheCaseDocx)
	@GetMapping("customerDetails/caseDetails/createJoiningTheCaseDocxForm")
	public String createJoiningTheCaseForm(@RequestParam("caseId") int caseId, Model model) {
		
		List<Court> courts = docGeneratorService.getAllCourts();
		LawCase theLawCase = customerService.getLawCaseById(caseId);
		
		// load from LawCase data to the "document generator form"
		int courtId = theLawCase.getCourt().getId();
		String firstName = theLawCase.getCustomer().getFirstName();
		String lastName = theLawCase.getCustomer().getLastName();
		String caseSignature = theLawCase.getSignature();
		
		model.addAttribute("courts", courts);
		model.addAttribute("joinTheCase", new JoiningTheCaseForm(courtId, firstName, lastName, caseSignature));
		
		// for button back "<<Powrót". If caseIdContion is not null it will go back to caseDetail page,
		// else it will go back to DocGenerater main menu from DocGeneratorController
		model.addAttribute("caseIdCondition", theLawCase.getCaseId());
		
		return "docGenerator/joinTheCase-docx-form";
	}
	
}
