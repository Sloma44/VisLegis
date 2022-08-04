package com.pioslomiany.VisLegis.calculator.validators;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrentDateValidator implements 
									ConstraintValidator<CurrentDateConstraint, LocalDate> {

	@Override
	public void initialize(CurrentDateConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
		
		if(date == null) {
			return false;
		}
		
		LocalDate currentDay = LocalDate.now();
		if (date.isEqual(currentDay) || date.isBefore(currentDay)) {
			return true;
		}
		
		return false;
	}

	
	
}
