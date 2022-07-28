package com.pioslomiany.DDSProject.doc.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.pioslomiany.DDSProject.calculator.validators.CurrentDateConstraint;

import lombok.Getter;
import lombok.Setter;

public class JustificationRequestForm extends DocxForm {
	
	@Setter
	private boolean verdict;
	
	@Getter @Setter
	@DateTimeFormat(iso = ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
	@CurrentDateConstraint
	private LocalDate verdictDate;
	
	@Setter
	private boolean costFree;
	
	public JustificationRequestForm () {
		super();
	}

	public boolean getVerdict() {
		return verdict;
	}

	public boolean getCostFree() {
		return costFree;
	}

}
