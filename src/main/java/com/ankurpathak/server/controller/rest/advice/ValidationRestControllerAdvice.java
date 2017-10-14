package com.ankurpathak.server.controller.rest.advice;

import com.ankurpathak.annotation.Exception;
import com.ankurpathak.server.controller.rest.RegisterRestController;
import com.ankurpathak.server.dto.ValidationErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ankurpathak.server.controller.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ankur on 26-10-2016.
 */
@RestControllerAdvice(basePackageClasses = RegisterRestController.class)
@Exception
public class ValidationRestControllerAdvice extends ResponseEntityExceptionHandler {


    @Autowired
    private MessageSource messageSource;



    private ValidationErrorDto processFieldErrors(List<FieldError> fieldErrors) {

        ValidationErrorDto validationErrorDto = new ValidationErrorDto();


        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedFieldErrorMessage(fieldError);
            validationErrorDto.addError(fieldError.getField(), localizedErrorMessage);
        }

        return validationErrorDto;
    }




    private String resolveLocalizedFieldErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }



    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<?> handleValidationException(ValidationException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationErrorDto validationErrorDto = processFieldErrors(fieldErrors);
        List<ObjectError> objectErrors = result.getGlobalErrors();
        List<String> starErrors=processGlobalErrors(objectErrors);
        for(String starError: starErrors){
            validationErrorDto.addError("*", starError);
        }
        validationErrorDto.setCode(0);
        validationErrorDto.setMessage("Validation failed");
        return super.handleExceptionInternal(ex, validationErrorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    private List<String> processGlobalErrors(List<ObjectError> objectErrors) {
        List<String> errors =  new ArrayList<>();
        for(ObjectError objectError: objectErrors){
            String localizedErrorMessage = resolveLocalizedObjectErrorMessage(objectError);
            errors.add(localizedErrorMessage);
        }
        return errors;
    }

    private String resolveLocalizedObjectErrorMessage(ObjectError objectError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(objectError.getDefaultMessage())) {
            String[] objectErrorCodes = objectError.getCodes();
            localizedErrorMessage = objectErrorCodes[0];
        }

        return localizedErrorMessage;
    }
}
