package com.pioslomiany.DDSProject.calculator.entity;


import javax.validation.constraints.NotEmpty;

import com.pioslomiany.DDSProject.calculator.validators.CurrentDateConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DateRangeForm {
	
	@NotEmpty(message="Błędna data")
	private String startDate;
	
	@CurrentDateConstraint
	private String endDate;
	
	
}
