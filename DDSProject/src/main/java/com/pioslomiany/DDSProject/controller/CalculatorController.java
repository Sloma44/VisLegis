package com.pioslomiany.DDSProject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pioslomiany.DDSProject.calculator.CriminalCalculator;
import com.pioslomiany.DDSProject.calculator.OneResultInstance;
import com.pioslomiany.DDSProject.calculator.OperatingCriminal;
import com.pioslomiany.DDSProject.calculator.PreparatoryProceeding;

@Controller
@RequestMapping("/dds/calculator")
public class CalculatorController {
	
	private CriminalCalculator criminalCalculator;
	
	private OperatingCriminal operatingCriminal;
	
//	private static List<OneResult> results;
//	
//	static {
//		results = new ArrayList<>();
//		
//		results.add(new OneResult("Postępowanie przyg", 180.00, 41.40, 221.40));
//		results.add(new OneResult("Pierwsza instancja", 420.00, 251.52, 648.58));
//		results.add(new OneResult("Druga instancja", 325.85, 400.00, 1000.25));
//		results.add(new OneResult("Pierwsza instancja", 582.00, 256.25, 584.25));
//		results.add(new OneResult("Druga instancja", 58.56, 5.2, 25.12));
//	}

	@GetMapping("")
	public String getMainCalculator() {
		return "calculator/main-calculator";
	}
	
	@GetMapping("criminalCalculatorFormPart1")
	public String getCriminalCalculatorFormPart1(Model model) {
		
		criminalCalculator = new CriminalCalculator();
		
		model.addAttribute("criminal", criminalCalculator);
		
		return "calculator/criminal-calculator-1";
	}
	
	@PostMapping("saveCriminalCalculatorFormPart1")
	public String saveCriminalCalculatorFormPart1(@ModelAttribute("criminal") CriminalCalculator criminalCalculator, Model model) {
		
		this.criminalCalculator = criminalCalculator;
		
		
		return "redirect:/dds/calculator/criminalCalculatorFormPart2";
	}

//miesjce zmiany jest tutaj
	@GetMapping("criminalCalculatorFormPart2")
	public String getCriminalCalculatorFormPart2(Model model) {
		
		System.out.println(criminalCalculator.toString());
		
		operatingCriminal = new OperatingCriminal(criminalCalculator);
		operatingCriminal.buildCriminalCalculation();
	
		PreparatoryProceeding thePreparatoryProceeding = operatingCriminal.getPp();
		List<OneResultInstance> resultList = operatingCriminal.getResultsList();

		model.addAttribute("prepProc", thePreparatoryProceeding);
		model.addAttribute("results", resultList);
		
		
		System.out.println("TUTAJ JEST DOBRE MIEJSE DO TESTÓW");	
		
		return "calculator/criminal-calculator-2";
	}
	
	@PostMapping("saveCriminalCalculatorFormPart2")
	public String saveCriminalCalculatorFormPart2(@ModelAttribute("criminal") CriminalCalculator criminalCalculator, Model model) {
		
		//to raczej do poprawy
//		this.criminalCalculator = criminalCalculator;
		
		return "redirect:/dds/calculator/criminalResult";
	}
	
	@GetMapping("criminalResult")
	public String getCriminalCalculatorResult(Model model) {
		
		model.addAttribute("criminal", criminalCalculator);
		
		return "calculator/criminal-calculator-result";
	}
	
}
