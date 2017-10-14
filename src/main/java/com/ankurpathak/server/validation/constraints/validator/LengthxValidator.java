package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.Lengthx;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ankur on 07-04-2017.
 */
public class LengthxValidator implements ConstraintValidator<Lengthx, CharSequence> {

    private static final Log log = LoggerFactory.make();


    private int min;
    private int max;
    private String label;

    public void initialize(Lengthx parameters) {
        this.min = parameters.min();
        this.max = parameters.max();
        this.label = label;
        this.validateParameters();
    }

    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) {
            return true;
        } else {
            int length = value.length();
            return length >= this.min && length <= this.max;
        }
    }

    private void validateParameters() {
        if(this.min < 0) {
            throw log.getMinCannotBeNegativeException();
        } else if(this.max < 0) {
            throw log.getMaxCannotBeNegativeException();
        } else if(this.max < this.min) {
            throw log.getLengthCannotBeNegativeException();
        }
    }
}
