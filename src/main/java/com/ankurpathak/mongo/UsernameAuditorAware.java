package com.ankurpathak.mongo;

import com.ankurpathak.security.CustomUserDetails;
import com.ankurpathak.security.CustomUserDetailsService;
import com.ankurpathak.server.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by ankur on 13-08-2016.
 */
@Component
public class UsernameAuditorAware implements AuditorAware<User> {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    public User getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!= null){
            String principal = (String) authentication.getPrincipal();
            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(principal);
            return userDetails.getUser();
        }

        return null;
    }
}
