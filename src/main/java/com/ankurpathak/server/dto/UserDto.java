package com.ankurpathak.server.dto;

import com.ankurpathak.server.validation.constraints.*;
import com.ankurpathak.server.validation.constraints.groups.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

/**
 * Created by ankur on 03-02-2017.
 */
@PasswordMatches(groups = Nine.class)
public class UserDto {


    @NotEmpty(groups = Zero.class)
    @Email(groups = One.class)
    private String email;
    //@Password
    @NotEmpty(groups = Zero.class)
    @Length(min = 8, max = 20, groups = One.class)
    @ContainUppercase(groups = Two.class)
    @ContainDigit(groups = Three.class)
    @ContainSpecial(groups = Four.class)
    @NotContainWhitespace(groups = Five.class)
    private String password;


    private String confirmPassword;


    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    //@Username
    @NotEmpty(groups = Zero.class)
    @Length(min =8, max = 30, groups = One.class)
    @Pattern(regexp = "[A-Za-z0-9._]+", groups = Two.class)
    @StartWithAlphaNumeric(groups = Three.class)
    @NotContainConsecutivePeriod(groups = Four.class)
    @NotContainConsecutiveUnderscore(groups = Five.class)
    @NotContainPeriodFollowedByUnderscore(groups = Six.class)
    @NotContainUnderscoreFollowedByPeriod(groups = Seven.class)
    @EndWithAlphaNumeric(groups = Eight.class)
    private String username;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
