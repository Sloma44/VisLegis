package com.pioslomiany.DDSProject.calculator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.calculator.dao.ChargeRatesEurDAO;
import com.pioslomiany.DDSProject.calculator.dao.CriminalCourtCostDAO;
import com.pioslomiany.DDSProject.calculator.dao.NBPExchangeRateDAO;
import com.pioslomiany.DDSProject.calculator.dao.ValuesDAO;
import com.pioslomiany.DDSProject.calculator.entity.ChargeRatesEur;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCost;
import com.pioslomiany.DDSProject.calculator.entity.CriminalCourtCostForm;
import com.pioslomiany.DDSProject.calculator.entity.PreparatoryProceeding;
import com.pioslomiany.DDSProject.calculator.entity.NBPExchangeRate.Rate;

@Service
public class CalculatorService {

	@Autowired
	ValuesDAO valuesDAO;
	
	@Autowired
	CriminalCourtCostDAO criminalCourtCostDAO;
	
	@Autowired
	NBPExchangeRateDAO nbpExchangeRateDAO;
	
	@Autowired
	ChargeRatesEurDAO chargesRatesEurDAO;
	
	@Transactional
	public double getEntityValueById(int theId) {
		return valuesDAO.getEntityValueById(theId);
	}

//	----------------------------------------------------------------
	public List<Rate> getLastDaysNBPExchangeRates(String startDate, String endDate) {
		return nbpExchangeRateDAO.getLastDaysNBPExchangeRates(startDate, endDate);
	}
	
	
	
//	----------------------------------------------------------------
	public List<ChargeRatesEur> getListOfChargeRatesEur(){
		return chargesRatesEurDAO.getListOfChargeRatesEur();
	}
	
	public void saveChargeRatesEur(ChargeRatesEur theChargeRatesEur) {
		chargesRatesEurDAO.saveChargeRatesEur(theChargeRatesEur);
	}
	
	public void resetChargeRateList() {
		chargesRatesEurDAO.resetChargeRateList();
	}
	
	public void deleteRecordByHashCode(int theHashCode) {
		chargesRatesEurDAO.deleteRecordByHashCode(theHashCode);
	}
	
//	----------------------------------------------------------------
	
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
