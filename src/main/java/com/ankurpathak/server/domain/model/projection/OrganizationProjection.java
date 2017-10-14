package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.server.domain.model.Location;
import com.ankurpathak.server.domain.model.Organization;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by ankur on 16-07-2017.
 */
@Projection(name = "detail", types = Organization.class)
public interface OrganizationProjection extends DomainProjection {
    String getName();
    Organization.Hierarchy getHierarchy();
    String getRef();
    LocationProjection getLocation();
    String getImgBaseUrl();
    String getImgUrl();
}
