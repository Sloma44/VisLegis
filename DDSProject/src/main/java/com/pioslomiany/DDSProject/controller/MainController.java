package com.pioslomiany.DDSProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pioslomiany.DDSProject.entity.CaseIncome;
import com.pioslomiany.DDSProject.entity.Letter;
import com.pioslomiany.DDSProject.entity.CourtHearingAgenda;
import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.entity.CustomerCaseCost;
import com.pioslomiany.DDSProject.entity.CustomerContactInfo;
import com.pioslomiany.DDSProject.entity.LawCase;
import com.pioslomiany.DDSProject.service.CustomerService;

@Controller
@RequestMapping("/dds")
public class MainController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String getCustomersList(Model model) {

		List<Customer> customerList = customerService.getAll();
		
		model.addAttribute("customers", customerList);	
		
		return "list-view";
	}
	
	@GetMapping("list/saveCustomerForm")
	public String saveCustomerForm(Model model) {
		
		model.addAttribute("customer", new Customer());
		model.addAttribute("customerContactInfo", new CustomerContactInfo());
		
		return "save-customer-form";
	}
	
	@GetMapping("list/updateCustomerForm")
	public String updateCustomerForm(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		CustomerContactInfo theCustomerContactInfo = customerService.getCustomerInfo(theCustomer);
		
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerContactInfo", theCustomerContactInfo);

		return "save-customer-form";
	}
	
	@PostMapping("/list/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer,
								@ModelAttribute("customerContactInfo") CustomerContactInfo theCustomerContactInfo,
								Model model) {
		
		customerService.saveCustomer(theCustomer);
		customerService.saveCustomerContactInfo(theCustomer, theCustomerContactInfo);
		
		return "redirect:/dds/list";
	}
	
	@GetMapping("/list/customerDetails")
	public String customerDetails(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		CustomerContactInfo theCustomerContactInfo = customerService.getCustomerInfo(theCustomer);
		List<LawCase> lawCaseList = customerService.getCustomerLawCases(theCustomer);
		
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerContactInfo", theCustomerContactInfo);
		model.addAttribute("lawCases", lawCaseList);
		
		return "customer-details";
	}
	
	@GetMapping("/list/customerDetails/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteCustomerById(theId);
		
		return "redirect:/dds/list";
	}
	
	@GetMapping("list/customerDetails/saveLawCaseForm")
	public String saveLawCaseForm(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		
		model.addAttribute("customer", theCustomer);
		model.addAttribute("lawCase", new LawCase());
		
		return "save-lawCase-form";
	}
	
	@GetMapping("/list/customerDetails/updateLawCaseForm")
	public String updateLawCaseForm(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		
		return "save-lawCase-form";
	}
	
	@PostMapping("/list/customerDetails/saveLawCase")
	public String saveLawCase(@ModelAttribute("lawCase") LawCase theLawCase,
								@ModelAttribute("customer") Customer theCustomer,
								Model model) {
		
		customerService.saveLawCase(theCustomer, theLawCase);
		
		return "redirect:/dds/list";
	}
	
	@GetMapping("list/customerDetails/caseDetails/deleteLawCase")
	public String deleteLawCase(@RequestParam("caseId") int theId) {
		
		LawCase lawCase = customerService.getLawCaseById(theId);
		int theCustomerId = lawCase.getCustomer().getId();
		
		customerService.deleteLawCaseById(theId);

		return "redirect:/dds/list/customerDetails?customerId=" + theCustomerId;
	}
	
	@GetMapping("list/customerDetails/caseDetails")
	public String caseDetails(@RequestParam("caseId") int theId, Model model) {
		
		LawCase theLawCase = customerService.getLawCaseById(theId);
		Customer theCustomer = theLawCase.getCustomer();
		List<CaseIncome> caseIncomeList = theLawCase.getCaseIncoms();
		List<Letter> theJournal = theLawCase.getCorrespondanceJournal();
		List<CustomerCaseCost> customerCaseCostList = theLawCase.getCustomerCaseCosts();
		List<CourtHearingAgenda> courtHearingAgendaList = theLawCase.getCourtHearingAgenda();
		
		model.addAttribute("lawCase", theLawCase);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("caseIncomes", caseIncomeList);
		model.addAttribute("journals", theJournal);
		model.addAttribute("customerCaseCosts", customerCaseCostList);
		model.addAttribute("courtAgenda", courtHearingAgendaList);
		
		return "lawCase-details";
	}
	
	
}
