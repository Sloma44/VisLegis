package com.pioslomiany.DDSProject.calculator.entity;

import lombok.Getter;

public class CriminalCourtPreparatoryProceedingCost {
	
	public final String name;
	@Getter
	public double baseValue;
	@Getter
	public double vatValue;
	@Getter
	public double sum;
	
	public CriminalCourtPreparatoryProceedingCost(double baseValue, double vatPrecentage) {
		name = "Postępowanie przygotowawcze";
		this.baseValue = baseValue;
		vatValue = baseValue * (vatPrecentage / 100);
		sum = baseValue + vatValue;
	}
}
