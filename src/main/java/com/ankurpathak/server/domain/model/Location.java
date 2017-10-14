package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotBlankx;
import com.ankurpathak.server.validation.constraints.NotNullx;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by ankur on 25-02-2017.
 */
public class Location implements Serializable{

    @Field("address")
    @NotBlankx(label = "Address")
    private String address;

    @DBRef
    @NotNullx(label = "Country")
    private Country country;

    @Field("city")
    @NotBlankx(label = "City")
    private String city;

    @Field("region")
    @NotBlankx(label = "Region")
    private String region;

    @Field("postalCode")
    @NotBlankx(label = "Postal Code")
    private String postalCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
