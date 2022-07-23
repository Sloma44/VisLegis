package com.pioslomiany.DDSProject.calculator.validators;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrentDateValidator implements 
									ConstraintValidator<CurrentDateConstraint, String> {

	@Override
	public void initialize(CurrentDateConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		
		if(date.equals("")) {
			return false;
		}
		
		LocalDate currentDay = LocalDate.now();
		LocalDate dateLD = LocalDate.parse(date);
		if (dateLD.isEqual(currentDay) || dateLD.isBefore(currentDay)) {
			return true;
		}
		
		return false;
	}

	
	
}
