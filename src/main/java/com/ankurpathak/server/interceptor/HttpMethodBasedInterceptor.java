package com.ankurpathak.server.interceptor;


import com.ankurpathak.security.CustomUserDetails;
import com.ankurpathak.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpMethodBasedInterceptor extends HandlerInterceptorAdapter{


    @Autowired
    private CustomUserDetailsService customUserDetailsService;




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals(HttpMethod.GET.name())){
            String organiaztionId = request.getParameter("organizationId");
            String path = request.getServletPath();
            if(path.startsWith("/api/v1")){
                if(path.endsWith("/api/v1/countries") || path.endsWith("/api/v1/countries/search/findAllByOrderByName"))
                    return  true;
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if(authentication!= null){
                    String principal = (String) authentication.getPrincipal();
                    CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(principal);
                    if(userDetails != null &&
                            userDetails.getUser() != null &&
                            userDetails.getUser().getOrganization() != null &&
                            userDetails.getUser().getOrganization().getId() != null &&
                            userDetails.getUser().getOrganization().getId().toString().equals(organiaztionId)){
                        return true;
                    }

                }
            }

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;

        }else if(request.getMethod().equals(HttpMethod.DELETE.name()) || request.getMethod().equals(HttpMethod.PUT.name())){
            return false;
        }
        else {
            return true;
        }

    }
}
