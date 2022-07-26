package com.pioslomiany.DDSProject.doc.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.pioslomiany.DDSProject.calculator.validators.CurrentDateConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProsecutorAccessionForm {

	@DateTimeFormat(iso = ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
	@CurrentDateConstraint
	private LocalDate actDate;
	
	@NotEmpty(message="Pole nie może być puste")
	private String destination;
	
	@NotEmpty(message="Pole nie może być puste")
	private String firstName;
	
	@NotEmpty(message="Pole nie może być puste")
	private String lastName;
	
	@NotEmpty(message="Pole nie może być puste")
	private String caseSignature;
	
}
