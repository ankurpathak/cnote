package com.ankurpathak.server.controller.exception;

/**
 * Created by ankur on 03-02-2017.
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
