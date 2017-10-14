package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.NotContainConsecutivePeriod;
import com.ankurpathak.server.validation.constraints.NotContainPeriodFollowedByUnderscore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ankur on 04-02-2017.
 */
public class NotContainPeriodFollowedByUnderscoreValidator implements ConstraintValidator<NotContainPeriodFollowedByUnderscore, String>{
    @Override
    public void initialize(NotContainPeriodFollowedByUnderscore notContainPeriodFollowedByUnderscore) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value !=null && !value.contains("._");
    }

}
