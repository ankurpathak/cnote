package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.jackson.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by ankur on 10-05-2017.
 */
public interface DomainProjection {
    @JsonSerialize(using = ObjectIdSerializer.class)
    ObjectId getId();
    Date getCreated();
    Date getUpdated();
}
