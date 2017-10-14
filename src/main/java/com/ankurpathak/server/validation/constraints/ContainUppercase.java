package com.ankurpathak.server.validation.constraints;

/**
 * Created by ankur on 04-02-2017.
 */

import com.ankurpathak.server.validation.constraints.validator.ContainUppercaseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ContainUppercaseValidator.class)
@Documented
public @interface ContainUppercase {


    String message() default "{com.ankurpathak.server.validation.constraints.ContainUppercase.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
