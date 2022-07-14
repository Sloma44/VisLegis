package com.pioslomiany.DDSProject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCost;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCostForm;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtPreparatoryProceedingCost;
import com.pioslomiany.DDSProject.calculator.service.CriminalCourtCostService;

@Controller
@RequestMapping("/dds/calculator")
public class CalculatorController {
	
	private CriminalCourtCostForm criminalCourtCostForm;
	
	private CriminalCourtCostService criminalCourtCostService;

	@GetMapping("")
	public String getMainCalculator() {
		return "calculator/main-calculator";
	}
	
	@GetMapping("criminalCalculatorForm")
	public String getCriminalCalculatorFormPart1(Model model) {
		
		criminalCourtCostForm = new CriminalCourtCostForm();
		
		model.addAttribute("criminal", criminalCourtCostForm);
		
		return "calculator/criminal-calculator-form";
	}
	
	@PostMapping("saveCriminalCalculatorForm")
	public String saveCriminalCalculatorFormPart1(@ModelAttribute("criminal") CriminalCourtCostForm criminalCourtCostForm, Model model) {
		
		this.criminalCourtCostForm = criminalCourtCostForm;
		
		
		return "redirect:/dds/calculator/criminalCalculatorResult";
	}

//miesjce zmiany jest tutaj
	@GetMapping("criminalCalculatorResult")
	public String getCriminalCalculatorFormPart2(Model model) {
		
		System.out.println(criminalCourtCostForm.toString());
		
		criminalCourtCostService = new CriminalCourtCostService(criminalCourtCostForm);
		criminalCourtCostService.buildCriminalCalculation();
	
		CriminalCourtPreparatoryProceedingCost thePreparatoryProceeding = criminalCourtCostService.getPp();
		List<CriminalCourtCost> resultList = criminalCourtCostService.getResultsList();

		model.addAttribute("prepProc", thePreparatoryProceeding);
		model.addAttribute("results", resultList);
		
		
		System.out.println("TUTAJ JEST DOBRE MIEJSE DO TESTÓW");	
		
		return "calculator/criminal-calculator-result";
	}
	
}
