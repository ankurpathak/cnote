package com.ankurpathak.server.controller.rest.advice;

import com.ankurpathak.annotation.Exception;
import com.ankurpathak.server.dto.ValidationErrorDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by ankur on 22-10-2016.
 */
@ControllerAdvice(basePackageClasses = RepositoryRestExceptionHandler.class)
@Exception
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestControllerAdvice{
    @ExceptionHandler(JsonParseException.class)
    protected ResponseEntity<?> handleJsonParseException(JsonParseException ex, WebRequest request) {
        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        validationErrorDto.setCode(2);
        validationErrorDto.setMessage("This is an invalid json. The request can not be parsed.");
        return ResponseEntity.badRequest().body(validationErrorDto);
    }


    @ExceptionHandler(UnrecognizedPropertyException.class)
    protected ResponseEntity<?> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex, WebRequest request) {

        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        validationErrorDto.setCode(2);
        validationErrorDto.setMessage("This is an invalid request. The field " + ex.getPropertyName() + " is not recognized by the system.");
        return ResponseEntity.badRequest().body(validationErrorDto);

    }


    @ExceptionHandler(JsonMappingException.class)
    protected ResponseEntity<?> handleJsonMappingException(JsonMappingException ex, WebRequest request) {
        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        validationErrorDto.setCode(2);
        validationErrorDto.setMessage("This is an invalid request. At least one field format is not readable by the system.");
        return ResponseEntity.badRequest().body(validationErrorDto);

    }
}
