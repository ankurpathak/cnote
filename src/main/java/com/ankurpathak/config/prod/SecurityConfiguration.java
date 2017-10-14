package com.ankurpathak.config.prod;

import com.ankurpathak.annotation.Prod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ankur on 19-02-2017.
 */
@Configuration
@EnableWebSecurity
@Prod
@Order(Ordered.LOWEST_PRECEDENCE)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        auth.authenticationProvider(provider);
    }






    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, res, ex)-> {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                })
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v2/forget-password").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/countries", "/api/v1/countries/search/findAllByOrderByName").permitAll()

                .anyRequest().authenticated()

                .and()

                .httpBasic()
                .and()
                .logout()
                .and()
                .csrf().disable();

    }




}
