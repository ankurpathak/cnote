package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.server.domain.model.User;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by ankur on 16-07-2017.
 */
@Projection(types = User.class)
public interface UserProjection extends UserBasicInformationProjection{
    String getContact();
    String getEmail();
    public LocationProjection getLocation();

}
