package com.rodez.com.Validator;


import jakarta.validation.Constraint;

@Constraint(validatedBy = ImmatriculationValidator.class)
public @interface ImmatriculationValidatorInterface {
    String message() default "L'immariculation est érronée";
    String regex();
}


