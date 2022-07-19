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
import com.pioslomiany.DDSProject.calculator.entity.NBPExchangeRate.Rate;
import com.pioslomiany.DDSProject.calculator.service.CalculatorService;
import com.pioslomiany.DDSProject.calculator.service.CriminalCourtCostService;

@Controller
@RequestMapping("/dds/calculator")
public class CalculatorController {
	
	@Autowired
	CalculatorService calculatorService;
	
	private CriminalCourtCostForm criminalCourtCostForm;
	
	private CriminalCourtCostService criminalCourtCostService;
	
	private String startDate;
	private String endDate;
	

	@GetMapping("")
	public String getMainCalculator() {
		return "calculator/main-calculator";
	}
	
	@GetMapping("criminalCalculatorForm")
	public String getCriminalCalculatorForm(Model model) {
		
		criminalCourtCostForm = new CriminalCourtCostForm();
		
		model.addAttribute("criminal", criminalCourtCostForm);
		
		return "calculator/criminal-calculator-form";
	}
	
	@PostMapping("saveCriminalCalculatorForm")
	public String saveCriminalCalculatorForm(@Valid @ModelAttribute("criminal") CriminalCourtCostForm criminalCourtCostForm, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "calculator/criminal-calculator-form";
		}
		
		this.criminalCourtCostForm = criminalCourtCostForm;
		
		return "redirect:/dds/calculator/criminalCalculatorResult";
	}

	@GetMapping("criminalCalculatorResult")
	public String getCriminalCalculatorFormResult(Model model) {
		
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
	
	
// -------------------------------------------------------------------------
	
	
	@GetMapping("eurExchangeRateForm")
	public String getEurExchangeRateForm(Model model) {
		
		model.addAttribute("startDate", new String());
		model.addAttribute("endDate", new String());
		
		return "calculator/eur-exchange-rate-form";
	}
	
	@PostMapping("saveEurExchangeRateForm")
	public String saveEurExchangeRateForm(@ModelAttribute("startDate") String startDate,
										@ModelAttribute("endDate") String endDate, Model model) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		
		return "redirect:/dds/calculator/eurExchangeRateResult";
	}
	
	@GetMapping("eurExchangeRateResult")
	public String eurExchangeRateResult(Model model) {
		
		List<Rate> lastDaysList = calculatorService.getLastDaysNBPExchangeRates(startDate, endDate);
		
		model.addAttribute("rates", lastDaysList);
		
		return "calculator/eur-exchange-rate-result";
	}
	
//	----------------------------------------------------------------
	
	@GetMapping("eurExchangeRateDay")
	public String getEurExchangeRateDay() {
		
		return "calculator/eur-exchange-rate-day";
	}
	
}
