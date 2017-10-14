package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.ContainDigit;
import com.ankurpathak.server.validation.constraints.NotContainWhitespace;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by ankur on 04-02-2017.
 */
public class NotContainWhitespaceValidator implements ConstraintValidator<NotContainWhitespace, String>{
    @Override
    public void initialize(NotContainWhitespace notContainWhitespace) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null)
            return false;
        RuleResult result = new PasswordValidator(Arrays.asList(new WhitespaceRule())).validate(new PasswordData(value));
        return result.isValid();
    }

}
