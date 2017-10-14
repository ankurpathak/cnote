package com.ankurpathak.security.oauth2;

import com.ankurpathak.server.domain.model.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ankur on 11-03-2017.
 */
public class CustomClientDetails implements ClientDetails {

    private Client client;
    private String clientId;
    private String clientSecret;
    private Set<String> resourceIds;
    private Set<String> authorizedGrantTypes;
    private Set<String> scopes;
    private Set<String> registeredRedirectUris;
    private List<GrantedAuthority> authorities;
    private Integer acccessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Set<String> autoApproveScopes;


    public CustomClientDetails() {
    }

    public CustomClientDetails(Client client) {
        this.client = client;
        clientId = client.getClientId();
        clientSecret = client.getClientSecret();
        resourceIds = client.getResourceIds() != null ? client.getResourceIds() : Collections.emptySet();
        authorizedGrantTypes = client.getAuthorizedGrantTypes() != null ? client.getAuthorizedGrantTypes().stream().map(Client.AuthorizedGrantType::name).collect(Collectors.toSet()) : Collections.emptySet();
        scopes =  client.getScopes() != null ? client.getScopes() : Collections.emptySet();
        registeredRedirectUris = client.getRegisteredRedirectUris() != null ? client.getRegisteredRedirectUris() : Collections.emptySet();
        acccessTokenValiditySeconds = client.getAcccessTokenValiditySeconds() != null ? client.getAcccessTokenValiditySeconds() : 0;
        refreshTokenValiditySeconds = client.getRefreshTokenValiditySeconds() != null ? client.getRefreshTokenValiditySeconds(): 0;
        autoApproveScopes = client.getAutoApproveScopes() != null ? client.getAutoApproveScopes() : Collections.emptySet();
        Set<String> roles = client.getRoles() != null ? client.getRoles() : Collections.emptySet();
        String rolesString = StringUtils.collectionToDelimitedString(client.getRoles(), ",");
        authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(rolesString);
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return this.getClientSecret() != null;
    }

    @Override
    public String getClientSecret() {
        return client.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return this.getScope() != null && ! this.getScope().isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {

        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUris;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return acccessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return autoApproveScopes.contains(scope);
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }
}
