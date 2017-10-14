package com.ankurpathak.server.domain.model.projection;

/**
 * Created by ankur on 10-05-2017.
 */
public interface DomainExtendedProjection extends DomainProjection {
    UserBasicInformationProjection getCreatedBy();
    UserBasicInformationProjection getUpdatedBy();
}
