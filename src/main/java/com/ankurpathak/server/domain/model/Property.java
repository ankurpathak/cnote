package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotBlankx;
import org.springframework.data.mongodb.core.mapping.Field;

public class Property {

    @Field("name")
    @NotBlankx(label = "Name")
    public String name;
    @Field("value")
    @NotBlankx(label = "Value")
    public Object value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
