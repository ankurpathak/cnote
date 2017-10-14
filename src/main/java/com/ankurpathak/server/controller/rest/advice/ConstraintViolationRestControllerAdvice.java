package com.ankurpathak.server.controller.rest.advice;

import com.ankurpathak.annotation.Exception;
import com.ankurpathak.server.dto.ValidationErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by ankur on 04-04-2017.
 */
@RestControllerAdvice
@Exception
public class ConstraintViolationRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> handleValidationException(ConstraintViolationException ex, WebRequest request) {
        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        constraintViolations.forEach(x -> {
            validationErrorDto.addError(x.getPropertyPath().toString(), x.getMessage());
        });
        validationErrorDto.setCode(0);
        validationErrorDto.setMessage("Validation failed");
        return super.handleExceptionInternal(ex, validationErrorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }
}
