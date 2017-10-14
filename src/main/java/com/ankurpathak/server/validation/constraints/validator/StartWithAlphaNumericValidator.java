package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.StartWithAlphaNumeric;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ankur on 04-02-2017.
 */
public class StartWithAlphaNumericValidator implements ConstraintValidator<StartWithAlphaNumeric, String>{
    @Override
    public void initialize(StartWithAlphaNumeric startWithAlphaNumeric) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        String start = value != null && value.length() > 0 ? value.substring(0, 1): "";

        return StringUtils.isAlphanumeric(start) && (StringUtils.isAllLowerCase(start) || StringUtils.isNumeric(start));
    }

}
