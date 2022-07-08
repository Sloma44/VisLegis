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
import com.pioslomiany.DDSProject.entity.CourtHearing;
import com.pioslomiany.DDSProject.entity.LawCase;
import com.pioslomiany.DDSProject.entity.Letter;
import com.pioslomiany.DDSProject.entity.views.CustomerCaseCourtHearingView;
import com.pioslomiany.DDSProject.entity.views.CustomerCaseIncomeView;
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
	public String getLetters(Model model) {
		
		List<CustomerCaseJournalView> theCustomerCaseJournalView = customerServiceViews.getAll();
		
		model.addAttribute("letters", theCustomerCaseJournalView);
		
		return "summary-letters";
		
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
	
	@GetMapping("/letters/deleteLetter")
	public String deleteLetter(@RequestParam("letterId") int letterId) {
		
		customerService.deleteLetterById(letterId);
		
		return "redirect:/dds/summary/letters";
	}
	
	@GetMapping("/courtHearings")
	public String getCourtHearings(Model model) {
		
		List<CustomerCaseCourtHearingView> theCourtHearings = customerServiceViews.getAllCourtHearings();
		
		model.addAttribute("hearings", theCourtHearings);
		
		return "summary-courtHearings";
	}
	
	@GetMapping("/courtHearings/saveHearingForm")
	public String saveCourtHearingForm(Model model) {
		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("hearing", new CourtHearing());
		
		return "save-hearing-form";
	}
	
	@GetMapping("/courtHearings/updateHearingForm")
	public String updateHearingForm(@RequestParam("hearingId") int hearingId, Model model) {
		
		CourtHearing theCourtHearing = customerService.getHearingById(hearingId);
		LawCase theLawCase = theCourtHearing.getLawCase();
		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("chosenLawCase", theLawCase);
		model.addAttribute("hearing", theCourtHearing);
		
		return "update-hearing-form";
	}
	
	@PostMapping("/courtHearings/saveHearing")
	public String saveHearing(@ModelAttribute("hearing") CourtHearing theCourtHearing,
								@ModelAttribute("lawCase") LawCase theLawCase, Model model) {
		
		customerService.saveCourtHearing(theLawCase, theCourtHearing);
		
		return "redirect:/dds/summary/courtHearings";
	}
	
	@GetMapping("/courtHearings/deleteHearing")
	public String deleteHearing(@RequestParam("hearingId") int hearingId) {
		
		customerService.deleteHearingById(hearingId);
		
		return "redirect:/dds/summary/courtHearings";
	}
	
	@GetMapping("/incomes")
	public String getIncomes(Model model) {
		
		List<CustomerCaseIncomeView> customerCaseIncomeView = customerServiceViews.getAllIncomes();
		
		model.addAttribute("incomes", customerCaseIncomeView);
		
		return "summary-incomes";
	}
	
	@GetMapping("incomes/saveIncomeForm")
	public String saveIncomeForm(Model model) {
		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("income", new CaseIncome());
		
		return "save-income-form";
	}
	
	@GetMapping("incomes/updateIncomeForm")
	public String updateIncomeForm(@RequestParam("incomeId") int incomeId, Model model) {
		
		CaseIncome theIncome = customerService.getCaseIncomeById(incomeId);
		LawCase theLawCase = theIncome.getLawCase();
		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("chosenLawCase", theLawCase);
		model.addAttribute("income", theIncome);
		
		return "update-income-form";
	}
	
	@PostMapping("/incomes/saveIncome")
	public String saveIncome(@ModelAttribute("income") CaseIncome theCaseIncome,
								@ModelAttribute("lawCase") LawCase theLawCase, Model model) {
		
		customerService.saveCaseIncome(theLawCase, theCaseIncome);
		
		return "redirect:/dds/summary/incomes";
	}
	
	@GetMapping("/incomes/deleteIncome")
	public String deleteIncome(@RequestParam("incomeId") int incomeId) {
		
		customerService.deleteCaseIncomeById(incomeId);
		
		return "redirect:/dds/summary/incomes";
	}
	
}
