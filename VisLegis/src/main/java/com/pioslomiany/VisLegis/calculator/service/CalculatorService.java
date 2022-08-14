package com.pioslomiany.VisLegis.calculator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.VisLegis.calculator.dao.CriminalCourtCostDAO;
import com.pioslomiany.VisLegis.calculator.dao.LastDayOfTheMonthsNBPEuroRateDAO;
import com.pioslomiany.VisLegis.calculator.dao.RecompenseRateDAO;
import com.pioslomiany.VisLegis.calculator.dao.ValuesDAO;
import com.pioslomiany.VisLegis.calculator.entity.CriminalCourtCost;
import com.pioslomiany.VisLegis.calculator.entity.CriminalCourtCostForm;
import com.pioslomiany.VisLegis.calculator.entity.DateRangeForm;
import com.pioslomiany.VisLegis.calculator.entity.PreparatoryProceeding;
import com.pioslomiany.VisLegis.calculator.entity.RecompenseRate;
import com.pioslomiany.VisLegis.calculator.entity.Values;
import com.pioslomiany.VisLegis.calculator.entity.NBPExchangeRate.Rate;

@Service
public class CalculatorService {

	@Autowired
	ValuesDAO valuesDAO;
	
	@Autowired
	CriminalCourtCostDAO criminalCourtCostDAO;
	
	@Autowired
	LastDayOfTheMonthsNBPEuroRateDAO lastDayOfTheMonthsNBPEuroRateDAO;
	
	@Autowired
	RecompenseRateDAO recompenseRateDAO;
	

//	Values used in calculators ("Wartości do obliczeń")
	
	@Transactional
	public List<Values> getAllValues() {
		return valuesDAO.getAllValues();
	}
	
	@Transactional
	public double getEntityValueById(int theId) {
		return valuesDAO.getEntityValueById(theId);
	}
	
	@Transactional
	public void modifyValue(Values value) {
		valuesDAO.modifyValue(value);
	}
	
	@Transactional
	public Values getValueById(int theId) {
		return valuesDAO.getValueById(theId);
	}

	
//	LastDayOfTheMonthsNBPEuroRate - euroExchangeValues for the last days of the months from specified range
//	("Kurs euro NBP dla ostaniego dnia miesiąca")
	
	public void setStartEndDate(DateRangeForm dateRange) {
		lastDayOfTheMonthsNBPEuroRateDAO.setStartEndDate(dateRange);
	}
	
	public List<Rate> getLastDaysNBPExchangeRates() {
		return lastDayOfTheMonthsNBPEuroRateDAO.getLastDaysNBPExchangeRates();
	}
	
	
	
//	RecompanseRate Controller (the list of recompense for the customer for delays in payment) 
//	("Zryczałtowane odszkodowanie za opóźnienia w wpłatności")
	
	public List<RecompenseRate> getRecompenseRatesList(){
		return recompenseRateDAO.getRecompenseRatesList();
	}
	
	public void saveRecompenseRate(RecompenseRate theRecompenseRate) {
		recompenseRateDAO.saveRecompenseRate(theRecompenseRate);
	}
	
	public void resetRecompenseRatesList() {
		recompenseRateDAO.resetRecompenseRatesList();
	}
	
	public void deleteRecompenseRateByHashCode(int theHashCode) {
		recompenseRateDAO.deleteRecompenseRateByHashCode(theHashCode);
	}
	
	
//	Criminal calculator ("Koszt spraw karnych")
	
	public PreparatoryProceeding getPreparatoryProceeding() {
		return criminalCourtCostDAO.getPreparatoryProceeding();
	}
	
	public void setCriminalCourtCostForm(CriminalCourtCostForm criminalCourtCostForm) {
		criminalCourtCostDAO.setCriminalCourtCostForm(criminalCourtCostForm);
	}
	
	public void passCalculatorService(CalculatorService calculatorService) {
		criminalCourtCostDAO.setCalculatorService(calculatorService);
	}
	
	public List<CriminalCourtCost> getResultsList() {
		return criminalCourtCostDAO.getResultsList();
	}
	
	public List<Double> getAllCostsSums() {
		return criminalCourtCostDAO.getAllCostsSums();
	}
	
	public List<Double> getAllFirstInstancCostsSums() {
		return criminalCourtCostDAO.getAllFirstInstancCostsSums();
	}
	
	public List<Double> getAllSecondInstancCostsSums() {
		return criminalCourtCostDAO.getAllSecondInstancCostsSums();
	}
	
}
