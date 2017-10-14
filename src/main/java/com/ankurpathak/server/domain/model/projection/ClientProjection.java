package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.server.domain.model.Client;
import com.ankurpathak.server.domain.model.Organization;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by ankur on 10-05-2017.
 */
@Projection(name = "detail", types = Client.class)
public interface ClientProjection extends DomainProjection{
    OrganizationProjection getOrganization();
}
