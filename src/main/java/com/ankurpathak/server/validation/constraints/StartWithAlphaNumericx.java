package com.ankurpathak.server.validation.constraints;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Created by ankur on 07-04-2017.
 */
@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@StartWithAlphaNumeric
@ReportAsSingleViolation
public @interface StartWithAlphaNumericx {
    public abstract String label();
    public abstract Class<?>[] groups() default {};
    public abstract String message() default "{com.ankurpathak.server.validation.constraints.StartWithAlphaNumericx.message}";
    public abstract Class<? extends Payload>[] payload() default {};
}
