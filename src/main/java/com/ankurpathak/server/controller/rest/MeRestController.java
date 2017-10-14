package com.ankurpathak.server.controller.rest;

import com.ankurpathak.security.CustomUserDetails;
import com.ankurpathak.security.CustomUserDetailsService;
import com.ankurpathak.server.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by ankur on 10-05-2017.
 */
@RestController
@RequestMapping("/api/v2")
public class MeRestController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/me")
    ResponseEntity<?> me(Principal principal){
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(principal.getName());
            User user = customUserDetails.getUser();
            user.setCreatedBy(null);
            user.setUpdatedBy(null);
            return ResponseEntity.ok().body(user);
        }catch (UsernameNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
