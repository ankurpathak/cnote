package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.server.domain.model.User;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by ankur on 16-07-2017.
 */
@Projection(name = "detail", types = User.class)
public interface UserOrganizationProjection extends UserProjection {
    OrganizationProjection getOrganization();
}
