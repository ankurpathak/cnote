package com.ankurpathak.config;

import com.ankurpathak.server.domain.model.*;
import com.ankurpathak.server.interceptor.HttpMethodBasedInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ankur on 22-10-2016.
 */
@Configuration
public class RepositoryRestMvcConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.exposeIdsFor(
                Category.class,
                Country.class,
                Organization.class,
                Product.class,
                User.class,
                Division.class,
                SubCategory.class,
                SubDivision.class,
                Client.class,
                Customer.class,
                Enquiry.class
        );


    }

    @Bean
    HttpMethodBasedInterceptor httpMethodBasedInterceptor(){
        return new HttpMethodBasedInterceptor();
    }


    @Bean
    public MappedInterceptor myMappedInterceptor(HttpMethodBasedInterceptor httpMethodBasedInterceptor) {
        return new MappedInterceptor(new String[]{"/api/v1/**"}, httpMethodBasedInterceptor);
    }







}
