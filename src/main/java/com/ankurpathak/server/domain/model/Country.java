package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotBlankx;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by ankur on 25-02-2017.
 */
@Document(collection = "countries")
@QueryEntity
public class Country extends Domain implements Serializable{

    @Field("name")
    @NotBlankx(label = "Name")
    private String name;

    @Field("iso3")
    @Indexed(unique = true, sparse = true, name = "countriesIso3Idx")
    @NotBlankx(label = "Iso3")
    private String iso3;

    @Field("code")
    @NotBlankx(label = "Code")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Country() {
    }
}
