package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.Email;
import com.ankurpathak.server.validation.constraints.StartWithAlphaNumeric;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ankur on 04-02-2017.
 */
public class EmailValidator implements ConstraintValidator<Email, String>{
    @Override
    public void initialize(Email email) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(value);
    }

}
