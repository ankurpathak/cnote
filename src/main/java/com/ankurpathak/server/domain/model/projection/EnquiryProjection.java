package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.server.domain.model.Enquiry;
import com.ankurpathak.server.domain.model.EnquiryLineItem;
import com.ankurpathak.server.domain.model.Property;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Created by ankur on 16-07-2017.
 */
@Projection(name = "detail", types = Enquiry.class)
public interface EnquiryProjection extends DomainExtendedProjection {
    List<EnquiryLineItemProjection> getLineItems();
    String getRemark();
    CustomerProjection getCustomer();
    public List<Property> getFields();
}
