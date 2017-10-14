package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.NotContainConsecutivePeriod;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Created by ankur on 04-02-2017.
 */
public class NotContainConsecutivePeriodValidator implements ConstraintValidator<NotContainConsecutivePeriod, String>{
    @Override
    public void initialize(NotContainConsecutivePeriod notContainConsecutivePeriod) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        return value !=null && !value.contains("..");
    }

}
