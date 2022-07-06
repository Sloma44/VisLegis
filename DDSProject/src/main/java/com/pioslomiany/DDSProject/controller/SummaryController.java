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

import com.pioslomiany.DDSProject.entity.Letter;
import com.pioslomiany.DDSProject.entity.LawCase;
import com.pioslomiany.DDSProject.entity.views.CustomerCaseJournalView;
import com.pioslomiany.DDSProject.service.CustomerService;
import com.pioslomiany.DDSProject.service.views.CustomerServiceViews;

@Controller
@RequestMapping("/dds/summary")
public class SummaryController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerServiceViews customerServiceViews;
	
	@GetMapping("/letters")
	public String getSummary(Model model) {
		
		List<CustomerCaseJournalView> theCustomerCaseJournalView = customerServiceViews.getAll();
		
		model.addAttribute("letters", theCustomerCaseJournalView);
		
		return "summary-lists-main";
		
	}
	
	@GetMapping("letters/saveLetterForm")
	public String saveLetterForm(Model model) {
		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("letter", new Letter());
		
		
		return "save-letter-form";
	}
	
	@GetMapping("letters/updateLetterForm")
	public String updateLetterForm(@RequestParam("letterId") int letterId, Model model) {
		
		Letter theLetter = customerService.getLetterById(letterId);
		LawCase theLawCase = theLetter.getLawCase();
		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("chosenLawCase", theLawCase);
		model.addAttribute("letter", theLetter);
		
		return "update-letter-form";
	}
	
	@PostMapping("/letters/saveLetter")
	public String saveLetter(@ModelAttribute("letter") Letter theLetter,
								@ModelAttribute("lawCase") LawCase theLawCase, Model model) {
		
		customerService.saveLetter(theLawCase, theLetter);
		
		return "redirect:/dds/summary/letters";
	}
	
	
}
