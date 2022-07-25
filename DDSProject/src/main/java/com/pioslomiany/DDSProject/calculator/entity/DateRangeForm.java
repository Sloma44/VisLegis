package com.pioslomiany.DDSProject.calculator.entity;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.pioslomiany.DDSProject.calculator.validators.CurrentDateConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DateRangeForm {
	
	@CurrentDateConstraint
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	
	@CurrentDateConstraint
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;
	
	
}
