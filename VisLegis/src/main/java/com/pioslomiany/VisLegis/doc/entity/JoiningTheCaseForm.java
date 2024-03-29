package com.pioslomiany.VisLegis.doc.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.pioslomiany.VisLegis.calculator.validators.CurrentDateConstraint;

import lombok.Getter;
import lombok.Setter;

	/*
	 * Used in
	 * "Prokuratura wstąpienie" - ProsecutorAccesion
	 * "Wstąpienie do sprawy" - JoiningTheCase
	 */

@Getter @Setter
public class JoiningTheCaseForm implements Form {

	@DateTimeFormat(iso = ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
	@CurrentDateConstraint
	private LocalDate actDate;
	
	private int courtId;
	
	@NotEmpty(message="Pole nie może być puste")
	private String firstName;
	
	@NotEmpty(message="Pole nie może być puste")
	private String lastName;
	
	@NotEmpty(message="Pole nie może być puste")
	private String caseSignature;
	
	public JoiningTheCaseForm () {
		this.actDate = LocalDate.now();
	}
	
	public JoiningTheCaseForm(int courtId, String firstName, String lastName, String caseSignature) {
		this.actDate = LocalDate.now();
		this.courtId = courtId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.caseSignature = caseSignature;
	}

	@Override
	public LocalDate getVerdictDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getVerdict() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getValidity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCostFree() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
