package com.rodez.com.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class ImmatriculationValidator implements ConstraintValidator<ImmatriculationValidatorInterface, String> {
    private String regex;


    @Override
    public void initialize(ImmatriculationValidatorInterface constraintAnnotation){
        this.regex = constraintAnnotation.regex();
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null){
            return true;
        }
        else {
            return s.matches(regex);
        }
    }
}
