package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.Url;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ankur on 27-02-2017.
 */
public class UrlValidator implements ConstraintValidator<Url, String> {
    private Url url;
    @Override
    public void initialize(Url url) {
        this.url = url;
    }

    @Override
    public boolean isValid(String urlString, ConstraintValidatorContext constraintValidatorContext) {

        String[] schemes = {"http","https"};
                org.apache.commons.validator.routines.UrlValidator urlValidator = new org.apache.commons.validator.routines.UrlValidator(schemes);
        if(url.ignoreBlank()){
            if(StringUtils.isBlank(urlString))
                return true;
        }
        return urlValidator.isValid(urlString);
    }
}
