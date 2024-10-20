package com.github.alina.repl.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator  implements ConstraintValidator<IsEmail, String> {
    int  minLength;
    int maxLength;
    @Override
    public void initialize(IsEmail constraintAnnotation) {
        minLength= constraintAnnotation.minLength();
        maxLength = constraintAnnotation.maxLength();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (maxLength>=value.length() || minLength<=value.length()){
            return false;
        }
        return true;
    }
}
