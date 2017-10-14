package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.NotContainPeriodFollowedByUnderscore;
import com.ankurpathak.server.validation.constraints.NotContainUnderscoreFollowedByPeriod;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ankur on 04-02-2017.
 */
public class NotContainUnderscoreFollowedByPeriodValidator implements ConstraintValidator<NotContainUnderscoreFollowedByPeriod, String>{
    @Override
    public void initialize(NotContainUnderscoreFollowedByPeriod notContainUnderscoreFollowedByPeriod) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value !=null &&  !value.contains("_.");
    }

}
