package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.Customer;
import com.ankurpathak.server.domain.model.projection.CustomerProjection;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by ankur on 26-02-2017.
 */
@Repository
@RepositoryRestResource(excerptProjection = CustomerProjection.class)
public interface ICustomerRepository extends MongoRepository<Customer, ObjectId> {
    Page<Customer> findByOrganizationId(@Param("organizationId") ObjectId organizationId, Pageable pageable);

    Page<Customer> findByOrganizationIdOrderByNameAsc(@Param("organizationId") ObjectId organizationId, Pageable pageable);

    Page<Customer> findByOrganizationIdOrderByCompanyAsc(@Param("organizationId") ObjectId organizationId, Pageable pageable);
}