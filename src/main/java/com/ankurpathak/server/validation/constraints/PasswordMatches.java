package com.ankurpathak.server.validation.constraints;

import com.ankurpathak.server.validation.constraints.validator.PasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by ankur on 23-10-2016.
 */
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "{com.ankurpathak.server.validation.constraints.PasswordMatches.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}