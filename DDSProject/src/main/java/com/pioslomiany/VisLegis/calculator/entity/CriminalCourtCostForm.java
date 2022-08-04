package com.pioslomiany.VisLegis.calculator.entity;

import javax.validation.constraints.Pattern;

public class CriminalCourtCostForm {

	//false = "Z urzędu" / true = "Z wyboru"
	private boolean byChoice;
	
	//false = "Nie" / true = "Tak"
	private boolean preparatoryProceeding;
	
	//if preparatoryProceeding == true
	//false = "Śledztwo / true = "Dochodzenie"
	private boolean investigation;
	
	//false = "Rejonowy / Okręgowy" / true = "Okręgowy / Apelacyjny"
	private boolean higherCourt;

	//number of times the case was in first instance
	@Pattern(regexp="[0-9]+(,[0-9]+)*", message="Błędne dane")
	private String firstInstance;
	
	//number of times the case was in second instance
	@Pattern(regexp="[0-9]+(,[0-9]+)*", message="Błędne dane")
	private String secondInstance;

	public boolean getByChoice() {
		return byChoice;
	}

	public void setByChoice(boolean byChoice) {
		this.byChoice = byChoice;
	}

	public boolean getPreparatoryProceeding() {
		return preparatoryProceeding;
	}

	public void setPreparatoryProceeding(boolean preparatoryProceeding) {
		this.preparatoryProceeding = preparatoryProceeding;
	}

	public boolean getInvestigation() {
		return investigation;
	}

	public void setInvestigation(boolean investigation) {
		this.investigation = investigation;
	}

	public boolean getHigherCourt() {
		return higherCourt;
	}

	public void setHigherCourt(boolean higherCourt) {
		this.higherCourt = higherCourt;
	}

	public String getFirstInstance() {
		return firstInstance;
	}

	public void setFirstInstance(String firstInstance) {
		this.firstInstance = firstInstance;
	}

	public String getSecondInstance() {
		return secondInstance;
	}

	public void setSecondInstance(String secondInstance) {
		this.secondInstance = secondInstance;
	}
}
