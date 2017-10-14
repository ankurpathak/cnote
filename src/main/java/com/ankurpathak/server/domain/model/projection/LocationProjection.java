package com.ankurpathak.server.domain.model.projection;

import com.ankurpathak.server.domain.model.Country;
import com.ankurpathak.server.domain.model.Location;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by ankur on 16-07-2017.
 */
@Projection(types = Location.class)
public interface LocationProjection {
    String getAddress();
    Country getCountry();
    String getCity();
    String getRegion();
    String getPostalCode();

}
