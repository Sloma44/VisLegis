package com.pioslomiany.DDSProject.doc.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.pioslomiany.DDSProject.calculator.validators.CurrentDateConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DocxForm {

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
	
	public DocxForm () {
		this.actDate = LocalDate.now();
	}
	
	public DocxForm(int courtId, String firstName, String lastName, String caseSignature) {
		this.actDate = LocalDate.now();
		this.courtId = courtId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.caseSignature = caseSignature;
	}
	
}
