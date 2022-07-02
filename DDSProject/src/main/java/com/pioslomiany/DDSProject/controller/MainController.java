package com.pioslomiany.DDSProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pioslomiany.DDSProject.entity.Customer;
import com.pioslomiany.DDSProject.service.CustomerService;

@Controller
@RequestMapping("/dds")
public class MainController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String getCustomersList(Model model) {
//		
		List<Customer> customerList = customerService.getAll();
		
		model.addAttribute("customers", customerList);	
		
		return "list-view";
	}
	
}
