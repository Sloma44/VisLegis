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

import com.pioslomiany.DDSProject.entity.Customer;
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
		
		return "save-customer-form";
	}
	
	@GetMapping("list/updateCustomerForm")
	public String updateCustomerForm(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		
		model.addAttribute("customer", theCustomer);
		
		return "save-customer-form";
	}
	
	@PostMapping("/list/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer, Model model) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/dds/list";
	}
	
	@GetMapping("/list/customerDetails")
	public String customerDetails(@RequestParam("customerId") int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomerById(theId);
		model.addAttribute("customer", theCustomer);
		model.addAttribute("customerContactInfo", customerService.getCustomerInfo(theCustomer));
		
		return "customer-details";
	}
	
	@GetMapping("/list/customerDetails/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteCustomerById(theId);
		
		return "redirect:/dds/list";
	}
	
}
