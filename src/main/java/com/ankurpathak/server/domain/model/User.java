package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by ankur on 05-02-2017.
 */
@Document(collection = "users")
@QueryEntity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User extends ExtendedDomain implements Serializable{

    public static final String USER_EMAIL_INDEX_NAME = "usersEmailIdx";
    public static final String USER_CONTACT_INDEX_NAME = "usersContactIdx";
    public static final String USER_USERNAME_INDEX_NAME = "usersUsernameIdx";



    @Field("email")
    @Indexed(unique = true, sparse = true)
    @NotBlankx(label = "Email")
    @Email
    private String email;

    @Field("username")
    @Indexed(unique = true, sparse = true)
    @NotBlankx(label = "Username")
    @Lengthx(label = "Username", min = 8, max = 30)
    @UsernamePattern
    @StartWithAlphaNumericx(label = "Username")
    @NotContainConsecutivePeriodx(label = "Username")
    @NotContainConsecutiveUnderscorex(label = "Username")
    @NotContainPeriodFollowedByUnderscorex(label = "Username")
    @NotContainUnderscoreFollowedByPeriodx(label = "Username")
    @EndWithAlphaNumericx(label = "Username")
    private String username;

    @Field("password")
    @NotBlankx(label = "Password")
    @Lengthx(label = "Password", min = 8, max = 30)
    @ContainUppercasex(label = "Password")
    @ContainDigitx(label = "Password")
    @ContainSpecialx(label = "Password")
    @NotContainWhitespacex(label = "Password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Field("firstName")
    @NotBlankx(label = "First Name")
    private String firstName;

    @Field("lastName")
    @NotBlankx(label = "Last Name")
    private String lastName;

    @Field("middleName")
    private String middleName;

    @Field("roles")
    @NotNullx(label = "Roles")
    @SizeOneOrMore(label = "Roles")
    private Set<Role> roles;

    @Field("contact")
    @NotBlankx(label = "Contact")
    @Contactx(label = "Contact")
    @Indexed(unique = true, sparse = true)
    private String contact;

    @Field("imgUrl")
    private String imgUrl;


    @Field("location")
    @NotNullx(label = "Location")
    @Valid
    private Location location;








    public enum Role{
        ROLE_ADMIN,
        ROLE_USER
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }



}
