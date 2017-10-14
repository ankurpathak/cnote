package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.server.domain.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;
import java.util.Map;

/**
 * Created by ankur on 30-06-2017.
 */
@Projection(name = "detail", types = Customer.class)
public interface CustomerProjection extends DomainProjection {
    String getEmail();
    String getName();
    String getCompany();
    String getContact();
    String getImgUrl();
    List<Property> getFields();
    String getImgUrl1();

}
