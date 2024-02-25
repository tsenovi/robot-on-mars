package com.comsystem.homework.util;

import com.comsystem.homework.constraint.PositiveNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PositiveNumberValidator implements ConstraintValidator<PositiveNumber, Integer> {

    @Override
    public void initialize(PositiveNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // Check if the value is a positive number greater than or equal to 1
        return value != null && value >= 1;
    }
}
