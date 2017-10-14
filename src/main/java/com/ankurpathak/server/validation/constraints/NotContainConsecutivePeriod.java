package com.ankurpathak.server.validation.constraints;

/**
 * Created by ankur on 04-02-2017.
 */

import com.ankurpathak.server.validation.constraints.validator.NotContainConsecutivePeriodValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotContainConsecutivePeriodValidator.class)
@Documented
public @interface NotContainConsecutivePeriod {


    String message() default "{com.ankurpathak.server.validation.constraints.NotContainConsecutivePeriod.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
