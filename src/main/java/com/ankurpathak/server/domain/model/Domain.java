package com.ankurpathak.server.domain.model;

import com.ankurpathak.jackson.ObjectIdSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ankur on 02-06-2017.
 */
public class Domain implements Serializable {
    @Field("id")
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;


    @CreatedDate
    @Field("created")
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    private Date created;

    @LastModifiedDate
    @Field("updated")
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")

    private Date updated;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }




}
