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
import org.springframework.web.bind.annotation.RequestParam;

import com.pioslomiany.DDSProject.calculator.entity.ChargeRatesEur;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCost;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCostForm;
import com.pioslomiany.DDSProject.calculator.entity.NBPExchangeRate.Rate;
import com.pioslomiany.DDSProject.calculator.entity.PreparatoryProceeding;
import com.pioslomiany.DDSProject.calculator.service.CalculatorService;

@Controller
@RequestMapping("/dds/calculator")
public class CalculatorController {
	
	@Autowired
	CalculatorService calculatorService;
	
	private String startDate;
	private String endDate;
	

	@GetMapping("")
	public String getMainCalculator() {
		return "calculator/main-calculator";
	}
	
	@GetMapping("criminalCalculatorForm")
	public String getCriminalCalculatorForm(Model model) {
		
		model.addAttribute("criminal", new CriminalCourtCostForm());
		
		return "calculator/criminal-calculator-form";
	}
	
	@PostMapping("saveCriminalCalculatorForm")
	public String saveCriminalCalculatorForm(@Valid @ModelAttribute("criminal") CriminalCourtCostForm criminalCourtCostForm, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "calculator/criminal-calculator-form";
		}
		
		calculatorService.setCriminalCourtCostForm(criminalCourtCostForm);
		
		return "redirect:/dds/calculator/criminalCalculatorResult";
	}

	@GetMapping("criminalCalculatorResult")
	public String getCriminalCalculatorFormResult(Model model) {
	
		//need to pass calculatorService to the DAO to have access to DB
		calculatorService.passCalculatorService(calculatorService);
		
		PreparatoryProceeding thePreparatoryProceeding = calculatorService.getPreparatoryProceeding();
		List<CriminalCourtCost> resultList = calculatorService.getResultsList();
		List<Double> allCostsSums = calculatorService.getAllCostsSums();
		List<Double> allFirstInstanceCostsSums = calculatorService.getAllFirstInstancCostsSums();
		List<Double> allSecondInstanceCostsSums = calculatorService.getAllSecondInstancCostsSums();
		
		double bonus = calculatorService.getEntityValueById(14);
//		
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
	
//	-------------------------------------------------------------------------
	
	@GetMapping("eurExchangeRateDay")
	public String getEurExchangeRateDay() {
		
		return "calculator/eur-exchange-rate-day";
	}
	
//	-------------------------------------------------------------------------
	
	@GetMapping("/chargeRatesEurForm")
	public String getChargeRatesEurForm(Model model) {
		
		model.addAttribute("chargeRatesEur", new ChargeRatesEur());
		
		return "calculator/charge-rates-calculator-form";
	}
	
	@PostMapping("/saveChargeRatesEurForm")
	public String saveChargeRatesEurForm(@ModelAttribute("chargeRatesEur") ChargeRatesEur chargeRatesEur) {
		
		String invoice = chargeRatesEur.getInvoice();
		String invoiceDate = chargeRatesEur.getInvoiceDate();
		String dateOfPayement = chargeRatesEur.getDateOfPayement();
		double valueGross = chargeRatesEur.getValueGross();

		calculatorService.saveChargeRatesEur(new ChargeRatesEur(invoice, invoiceDate, dateOfPayement, valueGross));
		
		return "redirect:/dds/calculator/chargeRatesEurResult";
	}
	
	@GetMapping("/chargeRatesEurResult")
	public String chargeRatesEurResult(Model model) {
		
		List<ChargeRatesEur> chargeRatesEurList = calculatorService.getListOfChargeRatesEur();
		
		model.addAttribute("chargeRatesList", chargeRatesEurList);
		
		return "calculator/charge-rates-calculator-result";
	}
	
	@GetMapping("/chargeRatesEurReset")
	public String chargeRatesEurReset() {
		
		calculatorService.resetChargeRateList();
		
		return "redirect:/dds/calculator/chargeRatesEurForm";
	}
	
	@GetMapping("/deleteOneRecord")
	public String deleteOneRecord(@RequestParam("chargeRates") Integer theHashCode, Model model) {

		calculatorService.deleteRecordByHashCode(theHashCode);
		
		return "redirect:/dds/calculator/chargeRatesEurResult";
	}
}
