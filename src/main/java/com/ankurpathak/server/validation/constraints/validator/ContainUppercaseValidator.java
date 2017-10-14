package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.ContainUppercase;
import org.apache.commons.lang3.StringUtils;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by ankur on 04-02-2017.
 */
public class ContainUppercaseValidator implements ConstraintValidator<ContainUppercase, String>{
    @Override
    public void initialize(ContainUppercase containUppercase) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null)
            return false;
        RuleResult result = new PasswordValidator(Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase))).validate(new PasswordData(value));
        return result.isValid();
    }

}
