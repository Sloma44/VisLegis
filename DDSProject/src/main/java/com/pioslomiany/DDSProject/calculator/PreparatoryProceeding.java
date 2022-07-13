package com.pioslomiany.DDSProject.calculator;

import lombok.Getter;
import lombok.Setter;

public class PreparatoryProceeding {
	
	public final String name;
	@Setter
	public double baseValue;
	@Getter
	public double vatValue;
	@Getter
	public double sum;
	
	public PreparatoryProceeding(double baseValue, double vatPrecentage) {
		name = "Postępowanie przygotowawcze";
		this.baseValue = baseValue;
		vatValue = baseValue * (vatPrecentage / 100);
		sum = baseValue + vatValue;
	}
	
//	public void countPreparatoryProceeding() {
//		vatValue = baseValue * (vatPrecentage / 100);
//		sum = baseValue + vatValue;
//	}
	
	public double getBaseValue() {
		return baseValue;
	}
}
