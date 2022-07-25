package com.pioslomiany.DDSProject.calculator.entity;

import com.pioslomiany.DDSProject.calculator.service.CalculatorService;

import lombok.Getter;

public class PreparatoryProceeding {
	
	private CriminalCourtCostForm criminalCourtCostForm;
	private CalculatorService calculatorService;
	
	public final String name;
	@Getter
	public double baseValue;
	@Getter
	public double vatValue;
	@Getter
	public double sum;
	
	public PreparatoryProceeding(CriminalCourtCostForm criminalCourtCostForm, CalculatorService calculatorService) {
		this.criminalCourtCostForm = criminalCourtCostForm;
		this.calculatorService = calculatorService;
		name = CriminalCalculatorConstances.PREPARATORY_PROCEEDING_NAME;
		buildPreparatoryProceeding();
	}
	
	private void buildPreparatoryProceeding() {
		double valueInvestigationLower;
		double valueInvestigationHigher;
		double vat;
		
		
		if (!criminalCourtCostForm.getByChoice()) {
			vat = calculatorService.getEntityValueById(15);
			valueInvestigationLower = calculatorService.getEntityValueById(1);
			valueInvestigationHigher = calculatorService.getEntityValueById(2);
		} else {
			vat = 0;
			valueInvestigationLower = calculatorService.getEntityValueById(7);
			valueInvestigationHigher = calculatorService.getEntityValueById(8);
		}	
		
		if(criminalCourtCostForm.getPreparatoryProceeding()) {
			if (criminalCourtCostForm.getInvestigation()) {
				countAllValues(valueInvestigationLower, vat);
			} else {
				countAllValues(valueInvestigationHigher, vat);
			}
		} else {
			countAllValues(0, 0);
		}
	}
	
	private void countAllValues(double baseValue, double vat) {
		this.baseValue = baseValue;
		this.vatValue = baseValue * (vat / 100);
		this.sum = baseValue + vatValue;
		this.vatValue = CriminalCalculatorConstances.roundUp(vatValue);
		this.sum = CriminalCalculatorConstances.roundUp(sum);
	}
}
