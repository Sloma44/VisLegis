package com.pioslomiany.DDSProject.calculator.entity;

public class CriminalCalculatorConstances {
	
	public static final String FIRST_INSTANCE_NAME = "I instancja";
	public static final String SECOND_INSTANCE_NAME = "II instancja";
	public static final String PREPARATORY_PROCEEDING_NAME = "Postępowanie przygotowawcze";
	
	
	public static double roundUp(double number) {
		return Math.round(number*100)/100.0d;
	}

}
