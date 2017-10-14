package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.ContainDigit;
import com.ankurpathak.server.validation.constraints.ContainSpecial;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by ankur on 04-02-2017.
 */
public class ContainSpecialValidator implements ConstraintValidator<ContainSpecial, String>{
    @Override
    public void initialize(ContainSpecial containSpecial) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null)
            return false;
        RuleResult result = new PasswordValidator(Arrays.asList(new CharacterRule(EnglishCharacterData.Special))).validate(new PasswordData(value));
        return result.isValid();
    }

}
