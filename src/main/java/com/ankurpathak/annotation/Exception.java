package com.ankurpathak.annotation;

/**
 * Created by ankur on 18-03-2017.
 */

import org.springframework.context.annotation.Profile;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Profile("exception")
 public @interface Exception {

}
