package com.ankurpathak.server.controller.exception;

import org.springframework.validation.BindingResult;

/**
 * Created by ankur on 03-02-2017.
 */
public class ValidationException extends RuntimeException {

    private BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }


    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
