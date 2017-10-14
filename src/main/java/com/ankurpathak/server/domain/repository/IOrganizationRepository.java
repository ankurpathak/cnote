package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.Organization;
import com.ankurpathak.server.domain.model.projection.OrganizationProjection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by ankur on 05-02-2017.
 */
@Repository
@RepositoryRestResource(excerptProjection = OrganizationProjection.class)
public interface IOrganizationRepository extends MongoRepository<Organization, ObjectId> {
}
