package com.ankurpathak.server.domain.listener;

import com.ankurpathak.security.CustomUserDetails;
import com.ankurpathak.security.CustomUserDetailsService;
import com.ankurpathak.server.domain.model.Domain;
import com.ankurpathak.server.domain.model.ExtendedDomain;
import com.ankurpathak.server.domain.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExtendedDomainEventHandler extends AbstractRepositoryEventListener<ExtendedDomain>{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void onBeforeCreate(ExtendedDomain entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!= null){
            String principal = (String) authentication.getPrincipal();
            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(principal);
            if(userDetails != null &&
               userDetails.getUser() != null &&
               userDetails.getUser().getOrganization() != null)
                    entity.setOrganization(userDetails.getUser().getOrganization());
        }
    }
}
