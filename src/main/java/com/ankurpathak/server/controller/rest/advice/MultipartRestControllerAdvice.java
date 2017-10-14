package com.ankurpathak.server.controller.rest.advice;

import com.ankurpathak.annotation.Exception;
import com.ankurpathak.server.controller.rest.RegisterRestController;
import com.ankurpathak.server.dto.ValidationErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by ankur on 10-05-2017.
 */
@RestControllerAdvice(basePackageClasses = RegisterRestController.class)
@Exception
public class MultipartRestControllerAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(MultipartException.class)
    protected ResponseEntity<?> handleValidationException(MultipartException ex, WebRequest request) {

        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        validationErrorDto.setCode(0);
        if(ex.getMessage().contains("field files"))
            validationErrorDto.setMessage(messageSource.getMessage("SizeLimit.filesDto.files", new Object[]{2},request.getLocale()));
        else if(ex.getMessage().contains("request")){
            validationErrorDto.setMessage(messageSource.getMessage("SizeLimit.filesDto.request", new Object[]{10},request.getLocale()));
        }
        return super.handleExceptionInternal(ex, validationErrorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }
}
