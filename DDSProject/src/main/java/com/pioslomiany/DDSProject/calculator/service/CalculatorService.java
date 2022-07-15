package com.pioslomiany.DDSProject.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.calculator.dao.ValuesDAO;

@Service
public class CalculatorService {

	@Autowired
	ValuesDAO valuesDAO;
	
	@Transactional
	public double getEntityValueById(int theId) {
		return valuesDAO.getEntityValueById(theId);
	}
	
}
