package com.ankurpathak.server.validation.constraints;

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
@NotContainConsecutiveUnderscore
@ReportAsSingleViolation
public @interface NotContainConsecutiveUnderscorex {
    public abstract String label();
    public abstract Class<?>[] groups() default {};
    public abstract String message() default "{com.ankurpathak.server.validation.constraints.NotContainConsecutiveUnderscorex.message}";
    public abstract Class<? extends Payload>[] payload() default {};
}
