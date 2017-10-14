package com.ankurpathak.server.validation.constraints;

/**
 * Created by ankur on 04-02-2017.
 */

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@NotEmpty
@Length(min =8, max = 30)
@Pattern(regexp = "[A-Za-z0-9._]+")
@StartWithAlphaNumeric
@NotContainConsecutivePeriod
@NotContainConsecutiveUnderscore
@NotContainPeriodFollowedByUnderscore
@NotContainUnderscoreFollowedByPeriod
@EndWithAlphaNumeric

//@ReportAsSingleViolation
public @interface Username {


    String message() default "{com.ankurpathak.server.validation.constraints.Password.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
