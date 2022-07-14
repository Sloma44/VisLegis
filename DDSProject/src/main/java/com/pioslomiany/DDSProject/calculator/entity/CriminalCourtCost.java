package com.pioslomiany.DDSProject.calculator.entity;

import lombok.Getter;

@Getter
public class CriminalCourtCost {
	
	 private final String name;
	 private double baseValueNet;
	 private double baseVatValue;
	 private double baseGross;
	 
	 private int numberOfHearings;
	 private double hearingValueNet;
	 private double hearingVatValue;
	 private double allHearingsGross;
	 
	 private double sumNet;
	 private double sumVat;
	 private double sumGross;
	
	public CriminalCourtCost (boolean isFirstInstance, double baseValueNet, double vat, int numberOfHearings, double percentageOfBaseValue) {
		if (isFirstInstance) {
			name = CriminalCalculatorConstances.FIRST_INSTANCE_NAME;
		} else {
			name = CriminalCalculatorConstances.SECOND_INSTANCE_NAME;
		}
		this.baseValueNet = baseValueNet;
		this.numberOfHearings = numberOfHearings;
		

		
		baseVatValue = baseValueNet * (vat / 100);
		baseGross = baseValueNet + baseVatValue;
		
		hearingValueNet = baseValueNet * (percentageOfBaseValue / 100);
		hearingVatValue = hearingValueNet * (vat / 100);
		allHearingsGross = (hearingValueNet + hearingVatValue) * (numberOfHearings - 1);

		sumNet = baseValueNet + (hearingValueNet * (numberOfHearings - 1));
		sumVat = baseVatValue + (hearingVatValue * (numberOfHearings - 1));
		sumGross = baseGross + allHearingsGross;

		baseVatValue = CriminalCalculatorConstances.roundUp(baseVatValue);
		baseGross = CriminalCalculatorConstances.roundUp(baseGross);
		
		hearingValueNet = CriminalCalculatorConstances.roundUp(hearingValueNet);
		hearingVatValue = CriminalCalculatorConstances.roundUp(hearingVatValue);
		allHearingsGross = CriminalCalculatorConstances.roundUp(allHearingsGross);
		
		sumNet = CriminalCalculatorConstances.roundUp(sumNet);
		sumVat = CriminalCalculatorConstances.roundUp(sumVat);
		sumGross = CriminalCalculatorConstances.roundUp(sumGross);
	}
	

}
