package com.ankurpathak.server.controller.rest.advice;

import com.ankurpathak.annotation.Exception;
import com.ankurpathak.server.domain.model.User;
import com.ankurpathak.server.dto.ValidationErrorDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by ankur on 04-04-2017.
 */
@RestControllerAdvice
@Exception
public class DuplicateKeyRestControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<?> handleValidationException(DuplicateKeyException ex, WebRequest request) {

        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        String message = "Duplicate Key";
        validationErrorDto.setCode(1);
        if(ex.getMessage().contains(User.USER_EMAIL_INDEX_NAME))
            message = "Email already exists.";
        else if(ex.getMessage().contains(User.USER_CONTACT_INDEX_NAME))
            message = "Contact already exists";
        else if(ex.getMessage().contains(User.USER_USERNAME_INDEX_NAME))
            message = "Username already exists.";

        validationErrorDto.setMessage(message);

        return super.handleExceptionInternal(ex, validationErrorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }
}
