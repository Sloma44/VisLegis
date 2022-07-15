package com.pioslomiany.DDSProject.controller;

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

import com.pioslomiany.DDSProject.entity.CaseIncome;
import com.pioslomiany.DDSProject.entity.CourtHearing;
import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.CustomerCaseCost;
import com.pioslomiany.DDSProject.entity.CustomerContactInfo;
import com.pioslomiany.DDSProject.entity.LawCase;
import com.pioslomiany.DDSProject.entity.Letter;
import com.pioslomiany.DDSProject.service.CustomerService;

@Controller
@RequestMapping("/dds/customerList")
public class CustomersListController {

	@Autowired
	private CustomerService customerService;
	
	
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
		
		return "list-view";
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
		
		return "customer-details";
	}
	
	
//	Actions (CRUD) on Customer and CustomerContactInfo entities
	@GetMapping("saveCustomerForm")
	public String saveCustomerForm(Model model) {
		
		model.addAttribute("customer", new Customer());
		model.addAttribute("customerContactInfo", new CustomerContactInfo());
		
		return "save-customer-form";
	}
	
	@GetMapping("updateCustomerForm")
	public String updateCustomerForm(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		CustomerContactInfo theCustomerContactInfo = customerService.getCustomerInfo(theCustomer);
		
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerContactInfo", theCustomerContactInfo);

		return "save-customer-form";
	}
	
	@PostMapping("saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult bindingResult,
			@ModelAttribute("customerContactInfo") CustomerContactInfo theCustomerContactInfo,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			return "save-customer-form";
		}
		
		customerService.saveCustomer(theCustomer);
		customerService.saveCustomerContactInfo(theCustomer, theCustomerContactInfo);
		
		int customerId = theCustomer.getId();
		
		return "redirect:/dds/customerList/customerDetails?customerId=" + customerId;
	}
	
	@GetMapping("customerDetails/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteCustomerById(theId);
		
		return "redirect:/dds/customerList";
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
		
		return "lawCase-details";
	}

	
//	Actions (CRUD) on LawCase entity
	@GetMapping("customerDetails/saveLawCaseForm")
	public String saveLawCaseForm(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		
		model.addAttribute("customer", theCustomer);
		model.addAttribute("lawCase", new LawCase());
		
		return "save-lawCase-form";
	}
	
	@GetMapping("customerDetails/updateLawCaseForm")
	public String updateLawCaseForm(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		
		return "save-lawCase-form";
	}
	
	@PostMapping("customerDetails/saveLawCase")
	public String saveLawCase(@ModelAttribute("lawCase") LawCase theLawCase,
								@ModelAttribute("customer") Customer theCustomer,
								Model model) {
		
		customerService.saveLawCase(theCustomer, theLawCase);
		
		int caseId = theLawCase.getCaseId();
		
		return "redirect:/dds/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	@GetMapping("customerDetails/caseDetails/deleteLawCase")
	public String deleteLawCase(@RequestParam("caseId") int theId) {
		
		LawCase lawCase = customerService.getLawCaseById(theId);
		int theCustomerId = lawCase.getCustomer().getId();
		
		customerService.deleteLawCaseById(theId);

		return "redirect:/dds/customerList/customerDetails?customerId=" + theCustomerId;
	}
	
	
	
//	Actions on Income, CustomerCaseCost, CourtHearings
	
//	Income
	@GetMapping("customerDetails/caseDetails/saveIncomeForm")
	public String saveIncomeForm(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("income", new CaseIncome());

		return "save-income-form-by-customer";
	}
	
	@GetMapping("customerDetails/caseDetails/updateIncomeForm")
	public String updateIncomeForm(@RequestParam("incomeId") int incomeId, Model model) {
		
		CaseIncome theCaseIncome = customerService.getCaseIncomeById(incomeId);
		LawCase theLawCase = theCaseIncome.getLawCase();		
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("income", theCaseIncome);
		
		return "save-income-form-by-customer";
	}
	
	@PostMapping("customerDetails/caseDetails/saveIncome")
	public String saveIncome(@ModelAttribute("lawCase") LawCase theLawCase,
							@ModelAttribute("income") CaseIncome theCaseIncome, Model model) {
		
		customerService.saveCaseIncome(theLawCase, theCaseIncome);
		
		int caseId = theLawCase.getCaseId();
		
		return "redirect:/dds/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	@GetMapping("customerDetails/caseDetails/deleteIncome")
	public String deleteIncome(@RequestParam("incomeId") int incomeId, Model model) {
		
		int caseId = customerService.getCaseIncomeById(incomeId).getLawCase().getCaseId();
		
		customerService.deleteCaseIncomeById(incomeId);
		
		return "redirect:/dds/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	
//	CustomerCaseCost
	@GetMapping("customerDetails/caseDetails/saveCustomerCaseCostForm")
	public String saveCustomerCaseCostForm(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerCaseCost", new CustomerCaseCost());

		return "save-customerCaseCost-form-by-customer";
	}
	
	@GetMapping("customerDetails/caseDetails/updateCustomerCaseCostForm")
	public String updateCustomerCaseCostForm(@RequestParam("customerCaseCostId") int customerCaseCostId, Model model) {
		
		CustomerCaseCost theCustomerCaseCost = customerService.getCustomerCaseCostById(customerCaseCostId);
		LawCase theLawCase = theCustomerCaseCost.getLawCase();		
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerCaseCost", theCustomerCaseCost);
		
		return "save-customerCaseCost-form-by-customer";
	}
	
	@PostMapping("customerDetails/caseDetails/saveCustomerCaseCost")
	public String saveCustomerCaseCost(@ModelAttribute("lawCase") LawCase theLawCase,
							@ModelAttribute("customerCaseCost") CustomerCaseCost theCustomerCaseCost, Model model) {
		
		customerService.saveCustomerCaseCost(theLawCase, theCustomerCaseCost);
		
		int caseId = theLawCase.getCaseId();
		
		return "redirect:/dds/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	@GetMapping("customerDetails/caseDetails/deleteCustomerCaseCost")
	public String deleteCustomerCaseCost(@RequestParam("customerCaseCostId") int customerCaseCostId, Model model) {
		
		int caseId = customerService.getCustomerCaseCostById(customerCaseCostId).getLawCase().getCaseId();
		
		customerService.deleteCustomerCaseCostById(customerCaseCostId);
		
		return "redirect:/dds/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	
//	CourtHearings
	@GetMapping("customerDetails/caseDetails/saveCourtHearingForm")
	public String saveCourtHearingForm(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("courtHearing", new CourtHearing());

		return "save-courtHearing-form-by-customer";
	}
	
	@GetMapping("customerDetails/caseDetails/updateCourtHearingForm")
	public String updateCourtHearingForm(@RequestParam("courtHearingId") int courtHearingId, Model model) {
		
		CourtHearing theCourtHearing = customerService.getHearingById(courtHearingId);
		LawCase theLawCase = theCourtHearing.getLawCase();		
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("courtHearing", theCourtHearing);
		
		return "save-courtHearing-form-by-customer";
	}
	
	@PostMapping("customerDetails/caseDetails/saveCourtHearing")
	public String saveCourtHearing(@ModelAttribute("lawCase") LawCase theLawCase,
							@ModelAttribute("courtHearing") CourtHearing theCourtHearing, Model model) {
		
		customerService.saveCourtHearing(theLawCase, theCourtHearing);
		
		int caseId = theLawCase.getCaseId();
		
		return "redirect:/dds/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
	@GetMapping("customerDetails/caseDetails/deleteCourtHearing")
	public String deleteCourtHearing(@RequestParam("courtHearingId") int courtHearingId, Model model) {
		
		int caseId = customerService.getHearingById(courtHearingId).getLawCase().getCaseId();
		
		customerService.deleteHearingById(courtHearingId);
		
		return "redirect:/dds/customerList/customerDetails/caseDetails?caseId=" + caseId;
	}
	
}
