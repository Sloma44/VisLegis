package com.pioslomiany.VisLegis.controller;

import java.time.LocalDate;
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

import com.pioslomiany.VisLegis.calculator.entity.CriminalCourtCost;
import com.pioslomiany.VisLegis.calculator.entity.CriminalCourtCostForm;
import com.pioslomiany.VisLegis.calculator.entity.DateRangeForm;
import com.pioslomiany.VisLegis.calculator.entity.PreparatoryProceeding;
import com.pioslomiany.VisLegis.calculator.entity.RecompenseRate;
import com.pioslomiany.VisLegis.calculator.entity.Values;
import com.pioslomiany.VisLegis.calculator.entity.NBPExchangeRate.Rate;
import com.pioslomiany.VisLegis.calculator.service.CalculatorService;

@Controller
@RequestMapping("/vislegis/calculator")
public class CalculatorController {
	
	@Autowired
	CalculatorService calculatorService;
	
	@GetMapping("")
	public String getMainCalculator() {
		return "calculator/main-calculator";
	}
	
	
	/* CriminalCourt Controller
	 * In this section user fills up a simple form (CriminalCourtCostForm) and as the results controller returns a list
	 * of first and second instance court costs in the form of a table.
	 * It is used to calculate the lawyer salary for the whole criminal case.
	 */
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
		
		return "redirect:/vislegis/calculator/criminalCalculatorResult";
	}

	@GetMapping("criminalCalculatorResult")
	public String getCriminalCalculatorFormResult(Model model) {
	
		//need to pass calculatorService to the DAO to have access to DB (table "constant_values")
		calculatorService.passCalculatorService(calculatorService);
		
		PreparatoryProceeding thePreparatoryProceeding = calculatorService.getPreparatoryProceeding();
		List<CriminalCourtCost> resultList = calculatorService.getResultsList();
		List<Double> allCostsSums = calculatorService.getAllCostsSums();
		List<Double> allFirstInstanceCostsSums = calculatorService.getAllFirstInstancCostsSums();
		List<Double> allSecondInstanceCostsSums = calculatorService.getAllSecondInstancCostsSums();
		
		double bonus = calculatorService.getEntityValueById(14);
		
		model.addAttribute("bonus", bonus);
		model.addAttribute("prepProc", thePreparatoryProceeding);
		model.addAttribute("results", resultList);
		
		model.addAttribute("sums", allCostsSums);
		model.addAttribute("firstInstanceSums", allFirstInstanceCostsSums);
		model.addAttribute("secondInstanceSums", allSecondInstanceCostsSums);	
		
		return "calculator/criminal-calculator-result";
	}
	
	

	/* LastDayOfTheMonthsNBPEuroRate Controller
	 * In this section user put two dates "startDate" and "endDate". The controller connects to NBP api and returns
	 * NBP euro rates for the last days of each month from the date range.
	 * NBP limits the request to maximum date range of 367 days. If the difference between startDate and endDate
	 * is greater then 367 days it narrows the startDate to the limit.
	 * This is a place for potential application development.
	 */
	
	@GetMapping("eurExchangeRateForm")
	public String getEurExchangeRateForm(Model model) {
		
		model.addAttribute("dateRange", new DateRangeForm());
		
		return "calculator/eur-exchange-rate-form";
	}
	
	@PostMapping("saveEurExchangeRateForm")
	public String saveEurExchangeRateForm(@Valid @ModelAttribute("dateRange") DateRangeForm dateRangeForm, BindingResult bindingResult,
										Model model) {
		
		if (bindingResult.hasErrors()) {
			return "redirect:/vislegis/calculator/eurExchangeRateForm";
		}
		
		calculatorService.setDateRange(dateRangeForm);
		
		return "redirect:/vislegis/calculator/eurExchangeRateResult";
	}
	
	@GetMapping("eurExchangeRateResult")
	public String eurExchangeRateResult(Model model) {
		
		List<Rate> lastDaysList = calculatorService.getLastDaysNBPExchangeRates();
		
		model.addAttribute("rates", lastDaysList);
		
		return "calculator/eur-exchange-rate-result";
	}
	

	/* One page section using JavaScript
	 * User put date and the application connect to NBP api to get the euro exchange date for specified date
	 */
	
	@GetMapping("eurExchangeRateDay")
	public String getEurExchangeRateDay() {
		
		return "calculator/eur-exchange-rate-day";
	}
	
	
	/*RecompanseRate Controller
	 * In this section user put incoice name, date, date of payement and value one by one to build
	 * a list of compensation for the delays in payement.
	 * It takes a date of payement add one day and takes the last day of previous months to connecto to NBP api
	 * and load the euro rate. Depending on the input value it counts the recompense for the client.
	 */
	
	@GetMapping("/recompanseRateForm")
	public String recompanseRateForm(Model model) {
		
		model.addAttribute("recompanseRate", new RecompenseRate());
		
		return "calculator/recompanse-rates-calculator-form";
	}
	
	@PostMapping("/saveRecompanseRateForm")
	public String saveRecompanseRateForm(@Valid @ModelAttribute("recompanseRate") RecompenseRate recompanseRate, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "redirect:/vislegis/calculator/recompanseRateForm";
		}
		
		String invoice = recompanseRate.getInvoice();
		LocalDate invoiceDate = recompanseRate.getInvoiceDate();
		LocalDate dateOfPayement = recompanseRate.getDateOfPayement();
		double valueGross = recompanseRate.getValueGross();

		calculatorService.saveRecompenseRate(new RecompenseRate(invoice, invoiceDate, dateOfPayement, valueGross));
		
		return "redirect:/vislegis/calculator/recompanseRateResult";
	}
	
	@GetMapping("/recompanseRateResult")
	public String recompanseRateResult(Model model) {
		
		List<RecompenseRate> recompanseRatesList = calculatorService.getRecompenseRatesList();
		
		model.addAttribute("recompanseRates", recompanseRatesList);
		
		return "calculator/recompanse-rates-calculator-result";
	}
	
	@GetMapping("/recompanseRatesListReset")
	public String recompanseRatesListReset() {
		
		calculatorService.resetRecompenseRatesList();
		
		return "redirect:/vislegis/calculator/recompanseRateForm";
	}
	
	@GetMapping("/deleteRecompanseRate")
	public String deleteRecompanseRate(@RequestParam("recompanseRate") Integer theHashCode, Model model) {

		calculatorService.deleteRecompenseRateByHashCode(theHashCode);
		
		return "redirect:/vislegis/calculator/recompanseRateResult";
	}
	
	
	/* Values Controller
	 * In this section user can check and modify the values from DB that are used in the calculators such as
	 *  for example VAT value. User can only modify them, cannot delete or add.
	 *  The values are used in other calculators and searched by ID so it cannot be changed and adding is also pointless
	 */
	
	@GetMapping("/values")
	public String getValues(Model model) {
		
		List<Values> values = calculatorService.getAllValues();
		
		model.addAttribute("values", values);
		
		return "calculator/values-calculator";
	}
	
	@GetMapping("/values/updateValueForm")
	public String updateValueForm(@RequestParam("valueId") int valueId, Model model) {
		
		Values value = calculatorService.getValueById(valueId);
		
		model.addAttribute("value", value);
		
		return "calculator/update-value-form";
	}
	
	@PostMapping("/values/updateValue")
	public String updateValue(@Valid @ModelAttribute("value") Values value, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "calculator/update-value-form";
		}
		
		calculatorService.modifyValue(value);
		
		return "redirect:/vislegis/calculator/values";
	}
}
