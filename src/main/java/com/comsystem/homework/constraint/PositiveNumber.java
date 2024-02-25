package com.comsystem.homework.constraint;

import com.comsystem.homework.util.PositiveNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositiveNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveNumber {

    String message() default "Value must be a positive number greater than or equal to 1";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
