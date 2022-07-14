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
			name = "I instancja";
		} else {
			name = "II instancja";
		}
		this.baseValueNet = baseValueNet;
		this.numberOfHearings = numberOfHearings;
		
		baseVatValue = baseValueNet * (vat / 100);
		baseGross = baseValueNet + baseVatValue;
		
		hearingValueNet = baseValueNet * (percentageOfBaseValue / 100);
		hearingVatValue = hearingValueNet * (vat / 100);
		allHearingsGross = (hearingValueNet + hearingVatValue) * (numberOfHearings - 1);
		
		sumNet = baseValueNet + allHearingsGross;
		sumVat = baseVatValue + (hearingVatValue * (numberOfHearings - 1));
		sumGross = baseGross + allHearingsGross;
	}
	
	

}
