package com.pioslomiany.DDSProject.calculator;

import lombok.Getter;

public class OneResultInstance {
	
	
	@Getter private final String name;
	@Getter private double baseValueNet;
	@Getter private double baseVatValue;
	@Getter private double baseGross;
	@Getter private int numberOfHearings;
	@Getter private double hearingValueNet;
	@Getter private double hearingVatValue;
	@Getter private double allHearingsGross;
	@Getter private double sumNet;
	@Getter private double sumVat;
	@Getter private double sumGross;
	
//	private double vat;
//	private double percentageOfBaseValue;
	
	public OneResultInstance (boolean isFirstInstance, double baseValueNet, double vat, int numberOfHearings, double percentageOfBaseValue) {
		if (isFirstInstance) {
			name = "I instancja";
		} else {
			name = "II instancja";
		}
		this.baseValueNet = baseValueNet;
		this.numberOfHearings = numberOfHearings;
//		this.vat = vat;
//		this.percentageOfBaseValue = percentageOfBaseValue;
		
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
