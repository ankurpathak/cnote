package com.ankurpathak.security.oauth2;

import com.ankurpathak.security.CustomUserDetails;
import com.ankurpathak.security.CustomUserDetailsService;
import com.ankurpathak.server.domain.model.Client;
import com.ankurpathak.server.domain.repository.IClientRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by ankur on 12-03-2017.
 */
@Component
public class CustomClientDetailsService implements ClientDetailsService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ObjectMapper objectMapper;



    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<Client> clientOpt = clientRepository.findByClientId(clientId);
        if(!clientOpt.isPresent()){
            throw new InvalidClientException(String.format("Can't find Client with clientId %s", clientId));
        }else{
            Client client = clientOpt.get();

            if(client.getOrganization() != null &&
                    client.getOrganization().getRef() != null &&
                    client.getOrganization().getRef().equals("srijandigital")){
                return new CustomClientDetails(client);
            }
            String username = getUsername();
            try{
                CustomUserDetails user =(CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
                if(user.getUser() != null &&
                        user.getUser().getOrganization() != null &&
                        client.getOrganization() != null &&
                        user.getUser().getOrganization().getId().toString().equals(client.getOrganization().getId().toString())){
                    return new CustomClientDetails(client);
                }else {
                    throw new InvalidClientException(String.format("Invalid Client with clientId %s for User with username %s", clientId, username));
                }
            }  catch (UsernameNotFoundException ex){
                throw new InvalidClientException(String.format("Invalid Client with clientId %s for User with username %s", clientId, username));
            }

        }
    }


    private String getUsername(){
        String username = null;
        username = request.getParameter("username");
        if(username == null){
            try {
                Authentication authentication = new BearerTokenExtractor().extract(request);
                Jwt help = JwtHelper.decode((String) authentication.getPrincipal());
                String claims  =  help.getClaims();
                JsonNode claimsNode = objectMapper.readTree(claims);
                JsonNode usernameNode = claimsNode.get("user_name");
                username = usernameNode.textValue();
            } catch (Exception e) {
                String refreshToken  = request.getParameter("refresh_token");
                if(refreshToken!= null){
                    try{
                        Jwt help = JwtHelper.decode(refreshToken);
                        String claims  =  help.getClaims();
                        JsonNode claimsNode = objectMapper.readTree(claims);
                        JsonNode usernameNode = claimsNode.get("user_name");
                        username = usernameNode.textValue();
                    }catch (Exception ex){
                        username = null;
                    }
                }else
                    username = null;
            }

        }
        return username;
    }

}
