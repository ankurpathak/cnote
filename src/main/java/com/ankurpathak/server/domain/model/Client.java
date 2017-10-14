package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotNullx;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by ankur on 10-03-2017.
 */
@Document(collection = "clients")
@QueryEntity
public class Client extends Domain implements Serializable {

    @Field("clientId")
    @Indexed(unique = true)
    private String clientId;


    @Field("organization")
    private Organization organization;


    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Field("clientSecret")
    private String clientSecret;


    @Field("resourceIds")
    private Set<String> resourceIds;

    @Field("authorizedGrantTypes")
    private Set<AuthorizedGrantType> authorizedGrantTypes;

    @Field("scopes")
    private Set<String> scopes;

    @Field("registeredRedirectUris")
    private Set<String> registeredRedirectUris;

    @Field("roles")
    private Set<String> roles;

    @Field("acccessTokenValiditySeconds")
    private Integer acccessTokenValiditySeconds;

    @Field("refreshTokenValiditySeconds")
    private Integer refreshTokenValiditySeconds;

    @Field("autoApproveScopes")
    private Set<String> autoApproveScopes;





    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Set<AuthorizedGrantType> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<AuthorizedGrantType> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
    }

    public Set<String> getRegisteredRedirectUris() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUris(Set<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


    public Integer getAcccessTokenValiditySeconds() {
        return acccessTokenValiditySeconds;
    }

    public void setAcccessTokenValiditySeconds(Integer acccessTokenValiditySeconds) {
        this.acccessTokenValiditySeconds = acccessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public Set<String> getAutoApproveScopes() {
        return autoApproveScopes;
    }

    public void setAutoApproveScopes(Set<String> autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }



    public enum AuthorizedGrantType {
        authorization_code,
        refresh_token,
        password,
        implicit,
        client_credentials

    }
}
