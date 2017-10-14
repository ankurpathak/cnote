package com.ankurpathak.config.dev;

import com.ankurpathak.annotation.Dev;
import com.ankurpathak.security.CustomUserDetailsService;
import com.ankurpathak.server.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by ankur on 03-02-2017.
 */
@Configuration
@Dev
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Autowired
    public PasswordEncoder passwordEncoder;




    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        /*
        auth.inMemoryAuthentication()
                .withUser("admin").password("password").roles("ADMIN", "USER")
                .and()
                .withUser("user").password("password").roles("USER");

                */
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);


    }







    @Configuration
    @Profile("dev")
    @Order(2)
    static class RootWebSecurityConfigurer extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/console/**").permitAll()
                    .antMatchers("/**").hasAuthority(User.Role.ROLE_ADMIN.name())
                    .and()
                    .headers()
                    .frameOptions()
                    .disable()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .httpBasic()
                    .and()
                    .csrf().disable();
        }
    }


    @Configuration
    @Profile("dev")
    @Order(1)
    static class ApiV1WebSecurityConfigurer extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/v1/**").authorizeRequests()
                    .anyRequest().hasAuthority(User.Role.ROLE_ADMIN.name())
                    .and()
                    .httpBasic()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf()
                    .disable();



        }
    }




}
