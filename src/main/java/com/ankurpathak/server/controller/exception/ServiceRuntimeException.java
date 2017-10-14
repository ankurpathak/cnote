package com.ankurpathak.server.controller.exception;


/**
 * Created by ankur on 03-02-2017.
 */
public class ServiceRuntimeException extends RuntimeException {


    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
