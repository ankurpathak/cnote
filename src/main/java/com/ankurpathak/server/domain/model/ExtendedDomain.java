package com.ankurpathak.server.domain.model;

import com.ankurpathak.server.validation.constraints.NotNullx;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by ankurpathak on 17-03-2016.
 */
public class ExtendedDomain extends Domain implements Serializable {

    private static final long serialVersionUID = 2L;








    @Field("createdBy")
    @CreatedBy
    @DBRef(lazy = true)
    private User createdBy;


    @Field("updatedBy")
    @LastModifiedBy
    @DBRef(lazy = true)
    private User updatedBy;


    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    @DBRef
    //@NotNullx(label = "Organization")
    private Organization organization;


    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
