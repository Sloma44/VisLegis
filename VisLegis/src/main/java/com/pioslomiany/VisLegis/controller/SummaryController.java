package com.pioslomiany.VisLegis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pioslomiany.VisLegis.customer.entity.CaseIncome;
import com.pioslomiany.VisLegis.customer.entity.CourtHearing;
import com.pioslomiany.VisLegis.customer.entity.LawCase;
import com.pioslomiany.VisLegis.customer.entity.Letter;
import com.pioslomiany.VisLegis.customer.service.CustomerService;
import com.pioslomiany.VisLegis.views.entity.CustomerCaseCourtHearingView;
import com.pioslomiany.VisLegis.views.entity.CustomerCaseIncomeView;
import com.pioslomiany.VisLegis.views.entity.CustomerCaseJournalView;
import com.pioslomiany.VisLegis.views.service.CustomerServiceViews;

@Controller
@RequestMapping("/vislegis/summary")
public class SummaryController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerServiceViews customerServiceViews;
	
	@GetMapping("")
	public String getSummary() {
		return "summary/summary-main";
	}
	
	// Letters
	/*Actions (CRUD) on Letters entity
		View is generated using MySQL VIEW and it combine Customer, LawCase and Letter entities.
		To generate view new entity was created - CustomerCaseJournalView.
		All actions are made only on Letter entity.
	*/
	
	@GetMapping("letters")
	public String getLetters(Model model) {
		
		List<CustomerCaseJournalView> theCustomerCaseJournalView = customerServiceViews.getAllJournals();
		
		model.addAttribute("letters", theCustomerCaseJournalView);
		
		return "summary/summary-letters";
	}
	
	@GetMapping("letters/saveLetterForm")
	public String saveLetterForm(Model model) {
		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("letter", new Letter());
		
		return "summary/save-letter-form";
	}
	
	@GetMapping("letters/updateLetterForm")
	public String updateLetterForm(@RequestParam("letterId") int letterId, Model model) {
		
		Letter theLetter = customerService.getLetterById(letterId);
		LawCase theLawCase = theLetter.getLawCase();		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("chosenLawCase", theLawCase);
		model.addAttribute("letter", theLetter);
		
		return "summary/save-letter-form";
	}
	
	@PostMapping("letters/saveLetter")
	public String saveLetter(@ModelAttribute("letter") Letter theLetter,
								Model model) {
		
		customerService.saveLetter(theLetter);
		
		return "redirect:/vislegis/summary/letters";
	}
	
	@GetMapping("letters/deleteLetter")
	public String deleteLetter(@RequestParam("letterId") int letterId) {
		
		customerService.deleteLetterById(letterId);
		
		return "redirect:/vislegis/summary/letters";
	}
	
	
	// CourtHearing
	/*Actions (CRUD) on CourtHearing entity
		View is generated using MySQL VIEW and it combine Customer, LawCase and CourtHearing.
		To generate view new entity was created - CustomerCaseCourtHearingView.
		All actions are made only on CourtHearing entity.
	*/
	
	@GetMapping("courtHearings")
	public String getCourtHearings(Model model) {
		
		List<CustomerCaseCourtHearingView> theCourtHearings = customerServiceViews.getAllCourtHearings();
		
		model.addAttribute("hearings", theCourtHearings);
		
		return "summary/summary-courtHearings";
	}
	
	@GetMapping("courtHearings/saveHearingForm")
	public String saveCourtHearingForm(Model model) {
		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("hearing", new CourtHearing());
		
		return "summary/save-hearing-form";
	}
	
	@GetMapping("courtHearings/updateHearingForm")
	public String updateHearingForm(@RequestParam("hearingId") int hearingId, Model model) {
		
		CourtHearing theCourtHearing = customerService.getHearingById(hearingId);
		LawCase theLawCase = theCourtHearing.getLawCase();
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("chosenLawCase", theLawCase);
		model.addAttribute("hearing", theCourtHearing);
		
		return "summary/save-hearing-form";
	}
	
	@PostMapping("courtHearings/saveHearing")
	public String saveHearing(@ModelAttribute("hearing") CourtHearing theCourtHearing,
								Model model) {
		
		customerService.saveCourtHearing(theCourtHearing);
		
		return "redirect:/vislegis/summary/courtHearings";
	}
	
	@GetMapping("courtHearings/deleteHearing")
	public String deleteHearing(@RequestParam("hearingId") int hearingId) {
		
		customerService.deleteHearingById(hearingId);
		
		return "redirect:/vislegis/summary/courtHearings";
	}
	
	
	// CaseIncome
	/*Actions (CRUD) on CaseIncome entity
		View is generated using MySQL VIEW and it combine Customer, LawCase and CaseIncome.
		To generate view new entity was created - CustomerCaseIncomeView.
		All actions are made only on CaseIncome entity.
	*/
	
	@GetMapping("incomes")
	public String getIncomes(Model model) {
		
		List<CustomerCaseIncomeView> customerCaseIncomeView = customerServiceViews.getAllIncomes();
		
		model.addAttribute("incomes", customerCaseIncomeView);
		
		return "summary/summary-incomes";
	}
	
	@GetMapping("incomes/saveIncomeForm")
	public String saveIncomeForm(Model model) {
		
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("income", new CaseIncome());
		
		return "summary/save-income-form";
	}
	
	@GetMapping("incomes/updateIncomeForm")
	public String updateIncomeForm(@RequestParam("incomeId") int incomeId, Model model) {
		
		CaseIncome theIncome = customerService.getCaseIncomeById(incomeId);
		LawCase theLawCase = theIncome.getLawCase();
		List<LawCase> lawCasesList = customerService.getAllLawCases();
		
		model.addAttribute("lawCases", lawCasesList);
		model.addAttribute("chosenLawCase", theLawCase);
		model.addAttribute("income", theIncome);
		
		return "summary/save-income-form";
	}
	
	@PostMapping("incomes/saveIncome")
	public String saveIncome(@ModelAttribute("income") CaseIncome theCaseIncome,
								Model model) {
		
		customerService.saveCaseIncome(theCaseIncome);
		
		return "redirect:/vislegis/summary/incomes";
	}
	
	@GetMapping("incomes/deleteIncome")
	public String deleteIncome(@RequestParam("incomeId") int incomeId) {
		
		customerService.deleteCaseIncomeById(incomeId);
		
		return "redirect:/vislegis/summary/incomes";
	}	
}
