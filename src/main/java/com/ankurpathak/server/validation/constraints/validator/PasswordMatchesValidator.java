package com.ankurpathak.server.validation.constraints.validator;


import com.ankurpathak.server.dto.UserDto;
import com.ankurpathak.server.validation.constraints.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ankur on 23-10-2016.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches passwordMatches) {

    }

    @Override
    public boolean isValid(Object objectDto, ConstraintValidatorContext constraintValidatorContext) {
        if(objectDto instanceof UserDto){
            UserDto userDto = (UserDto) objectDto;
            String password = userDto.getPassword() ;
            if(password == null)
                password = "";
            return password.equals(userDto.getConfirmPassword());
        }
        return false;
    }
}
