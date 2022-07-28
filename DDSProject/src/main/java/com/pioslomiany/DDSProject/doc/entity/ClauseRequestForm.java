package com.pioslomiany.DDSProject.doc.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.pioslomiany.DDSProject.calculator.validators.CurrentDateConstraint;

import lombok.Getter;
import lombok.Setter;

public class ClauseRequestForm {
	
	@Getter @Setter
	@DateTimeFormat(iso = ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
	@CurrentDateConstraint
	private LocalDate actDate;
	
	@Getter @Setter
	private int courtId;
	
	@Getter @Setter
	@NotEmpty(message="Pole nie może być puste")
	private String firstName;
	
	@Getter @Setter
	@NotEmpty(message="Pole nie może być puste")
	private String lastName;
	
	@Getter @Setter
	@NotEmpty(message="Pole nie może być puste")
	private String caseSignature;
	
	@Setter
	private boolean verdict;
	
	@Getter @Setter
	@DateTimeFormat(iso = ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
	@CurrentDateConstraint
	private LocalDate verdictDate;
	
	@Setter
	private boolean validity;
	
	@Setter
	private boolean costFree;
	
	public ClauseRequestForm () {
		this.actDate = LocalDate.now();
	}

	public boolean getVerdict() {
		return verdict;
	}

	public boolean getValidity() {
		return validity;
	}

	public boolean getCostFree() {
		return costFree;
	}

}
