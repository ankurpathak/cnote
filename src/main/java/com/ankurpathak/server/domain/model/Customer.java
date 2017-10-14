package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.Contact;
import com.ankurpathak.server.validation.constraints.Email;
import com.ankurpathak.server.validation.constraints.NotBlankx;
import com.ankurpathak.server.validation.constraints.NotNullx;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ankur on 26-02-2017.
 */
@Document(collection = "customers")
@QueryEntity
public class Customer extends ExtendedDomain implements Serializable {
    @Field("email")
    //@Indexed(unique = true)
    @NotBlankx(label = "Email")
    @Email
    private String email;

    @Field("name")
    //@NotBlankx(label = "Name")
    private String name;

    @Field("company")
    @NotBlankx(label = "Company")
    private String company;


    @Field("contact")
    //@Contact
    //@NotBlankx(label = "Contact")
    private String contact;

    @Field("imgUrl")
    //@NotBlankx(label = "Image Url")
    private String imgUrl;


    @Field("imgUrl1")
    //@NotBlankx(label = "Image Url")
    private String imgUrl1;


    public String getImgUrl1() {
        return imgUrl1;
    }

    public void setImgUrl1(String imgUrl1) {
        this.imgUrl1 = imgUrl1;
    }

    //@Field("location")
   // @NotNullx(label = "Location")
   // @Valid
   // private Location location;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }



    @Field("fields")
    private List<@Valid Property> fields;

    public List<Property> getFields() {
        return fields;
    }

    public void setFields(List<Property> fields) {
        this.fields = fields;
    }
}
