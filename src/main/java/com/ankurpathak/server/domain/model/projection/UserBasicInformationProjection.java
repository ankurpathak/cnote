package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.server.domain.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

/**
 * Created by ankur on 16-07-2017.
 */
@Projection(types = User.class)
public interface UserBasicInformationProjection extends DomainProjection {
    String getUsername();
    String getFirstName();
    String getLastName();
    Set<User.Role> getRoles();
    String getImgUrl();
    String getMiddleName();
}
