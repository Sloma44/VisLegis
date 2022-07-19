package com.pioslomiany.DDSProject.calculator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.calculator.dao.NBPExchangeRateDAO;
import com.pioslomiany.DDSProject.calculator.dao.ValuesDAO;
import com.pioslomiany.DDSProject.calculator.entity.NBPExchangeRate.Rate;

@Service
public class CalculatorService {

	@Autowired
	ValuesDAO valuesDAO;
	
	@Autowired
	NBPExchangeRateDAO nbpExchangeRateDAO;
	
	@Transactional
	public double getEntityValueById(int theId) {
		return valuesDAO.getEntityValueById(theId);
	}
	
	public List<Rate> getLastDaysNBPExchangeRates(String startDate, String endDate) {
		return nbpExchangeRateDAO.getLastDaysNBPExchangeRates(startDate, endDate);
	}
	
	
	
}
