package com.ankurpathak.server.validation.constraints;

import com.ankurpathak.server.validation.constraints.validator.ContactValidator;
import com.ankurpathak.server.validation.constraints.validator.LengthxValidator;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Created by ankur on 07-04-2017.
 */
@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LengthxValidator.class)
public @interface Lengthx {
    public abstract String label();
    int min() default 0;
    int max() default 2147483647;
    public abstract Class<?>[] groups() default {};
    public abstract String message() default "{com.ankurpathak.server.validation.constraints.Lengthx.message}";
    public abstract Class<? extends Payload>[] payload() default {};
}
