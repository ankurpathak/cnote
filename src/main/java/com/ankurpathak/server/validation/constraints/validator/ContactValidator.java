package com.ankurpathak.server.validation.constraints.validator;

import com.ankurpathak.server.validation.constraints.Contact;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ankur on 27-02-2017.
 */
public class ContactValidator implements ConstraintValidator<Contact, String> {
    @Override
    public void initialize(Contact contact) {

    }

    @Override
    public boolean isValid(String contact, ConstraintValidatorContext constraintValidatorContext) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber result = phoneNumberUtil.parse(contact, "");
            return phoneNumberUtil.isValidNumber(result);
        } catch (NumberParseException e) {
            return false;
        }
    }
}
