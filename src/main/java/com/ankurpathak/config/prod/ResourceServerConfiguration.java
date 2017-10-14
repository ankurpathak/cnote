package com.ankurpathak.config.prod;

import com.ankurpathak.annotation.Prod;
import com.ankurpathak.security.CustomUserDetails;
import com.ankurpathak.security.CustomUserDetailsService;
import com.ankurpathak.server.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ankur on 09-03-2017.
 */
@Configuration
@EnableResourceServer
@Prod
class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{


    /*

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(customUserDetailsService);
        auth.authenticationProvider(provider);

    } */







    @Override
    public void configure(HttpSecurity http) throws Exception {


        http
                .antMatcher("/api/**")
                .requestMatcher(new OAuthRequestedMatcher())
                .authorizeRequests()
             //   .antMatchers(HttpMethod.GET,"/api/v2/forget-password").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/api/v1/divisions/search/findByOrganizationId",
                        "/api/v1/subDivisions/search/findByOrganizationIdAndDivisionId",
                        "/api/v1/products/search/findByOrganizationIdAndSubDivisionId",
                        "/api/v1/products/search/findByOrganizationId",
                        "/api/v1/clients/search/findByClientId",
                        "/api/v1/products/search/findByOrganizationIdAndDivisionId",
                        "/api/v2/me",
                        "/api/v1/enquiries/search/findByOrganizationIdAndCreatedById",
                        "/api/v1/enquiries/search/findByOrganizationIdAndCreatedByIdOrderByCreatedDesc",
                        "/api/v1/countries",
                        "/api/v1/countries/search/findAllOrderByName",
                        "/api/v1/countries/search/findByNameContainingIgnoreCase",
                        "/api/v1/customers/search/findByOrganizationId",
                        "/api/v1/customers/search/findByOrganizationIdOrderByNameAsc",
                        "/api/v1/customers/search/findByOrganizationIdOrderByCompanyAsc",
                        "/api/v1/enquiries/search/findByOrganizationIdAndCreatedByIdAndCreatedBetweenOrderByCreatedDesc"
                )
                .hasAuthority(User.Role.ROLE_USER.name())
                .antMatchers(HttpMethod.POST,
                        "/api/v1/enquiries",
                        "/api/v1/customers")
                .hasAuthority(User.Role.ROLE_USER.name())
                .antMatchers(HttpMethod.PATCH,
                        "/api/v1/users/*",
                        "/api/v1/customers/*")
                .hasAuthority(User.Role.ROLE_USER.name())



                .anyRequest().hasAuthority(User.Role.ROLE_ADMIN.name())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable();


    }




    private static class OAuthRequestedMatcher implements RequestMatcher {
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            // Determine if the client request contained an OAuth Authorization
            boolean haveOauth2Token = (auth != null) && (auth.startsWith("Bearer"));
            boolean haveAccessToken = request.getParameter("access_token")!=null;
            boolean haveRefreshToken = "refresh_token".equals(request.getParameter("grant_type"));
            return haveOauth2Token || haveAccessToken || haveRefreshToken;
        }
    }



}
