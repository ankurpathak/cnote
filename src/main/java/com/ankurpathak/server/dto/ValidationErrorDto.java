package com.ankurpathak.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ankur on 03-02-2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidationErrorDto {
    private String message;
    private Integer code;
    private Map<String, List<String>> errors = new HashMap<>();




    public void addError(String path, String message) {
        List<String> messages = null;
        if(errors.containsKey(path)){
            messages = errors.get(path);

        }else {
            messages = new ArrayList<>();
            errors.put(path, messages);
        }
        messages.add(message);


    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }
}
