package com.example.notesapi.util;

//import javax.validation.Constraint;
//import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.notesapi.constants.ValidationErrorConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface EmailExist {
    String message() default ValidationErrorConstants.USER_WITH_EMAIL_EXISTS;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
