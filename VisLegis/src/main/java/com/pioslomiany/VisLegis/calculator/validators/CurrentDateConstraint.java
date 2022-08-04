package com.pioslomiany.VisLegis.calculator.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CurrentDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentDateConstraint {

    String message() default "Błędna data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
}
