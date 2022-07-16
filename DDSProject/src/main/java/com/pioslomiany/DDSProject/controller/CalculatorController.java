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

import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCost;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCostForm;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtPreparatoryProceedingCost;
import com.pioslomiany.DDSProject.calculator.service.CalculatorService;
import com.pioslomiany.DDSProject.calculator.service.CriminalCourtCostService;

@Controller
@RequestMapping("/dds/calculator")
public class CalculatorController {
	
	@Autowired
	CalculatorService calculatorService;
	
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
	public String saveCriminalCalculatorFormPart1(@Valid @ModelAttribute("criminal") CriminalCourtCostForm criminalCourtCostForm, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "calculator/criminal-calculator-form";
		}
		
		this.criminalCourtCostForm = criminalCourtCostForm;
		
		return "redirect:/dds/calculator/criminalCalculatorResult";
	}

	@GetMapping("criminalCalculatorResult")
	public String getCriminalCalculatorFormPart2(Model model) {
		
		criminalCourtCostService = new CriminalCourtCostService(criminalCourtCostForm, calculatorService);
		criminalCourtCostService.buildCriminalCalculation();
	
		CriminalCourtPreparatoryProceedingCost thePreparatoryProceeding = criminalCourtCostService.getPreparatoryProceeding();
		List<CriminalCourtCost> resultList = criminalCourtCostService.getResultsList();
		List<Double> allCostsSums = criminalCourtCostService.getAllCostsSums();
		List<Double> allFirstInstanceCostsSums = criminalCourtCostService.getAllFirstInstancCostsSums();
		List<Double> allSecondInstanceCostsSums = criminalCourtCostService.getAllSecondInstancCostsSums();
		
		double bonus = calculatorService.getEntityValueById(14);
		
		model.addAttribute("bonus", bonus);
		model.addAttribute("prepProc", thePreparatoryProceeding);
		model.addAttribute("results", resultList);
		
		model.addAttribute("sums", allCostsSums);
		model.addAttribute("firstInstanceSums", allFirstInstanceCostsSums);
		model.addAttribute("secondInstanceSums", allSecondInstanceCostsSums);	
		
		return "calculator/criminal-calculator-result";
	}
	
}
