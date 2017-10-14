package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.server.domain.model.Product;
import com.ankurpathak.server.domain.model.Property;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;
import java.util.Map;

/**
 * Created by ankur on 16-07-2017.
 */
@Projection(types = EnquiryLineItemProjection.class)
public interface EnquiryLineItemProjection {
    String getRemark();
    List<Property> getFields();
    Product getProduct();
    Boolean getSample();

}
