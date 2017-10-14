package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.Enquiry;
import com.ankurpathak.server.domain.model.projection.EnquiryProjection;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by ankur on 26-02-2017.
 */
@Repository
@RepositoryRestResource(excerptProjection = EnquiryProjection.class)
public interface IEnquiryRepository extends MongoRepository<Enquiry, ObjectId> {
    Page<Enquiry> findByOrganizationIdAndCreatedById(@Param("organizationId") ObjectId organizationId, @Param("createdById") ObjectId createdId, Pageable pageable);
    Page<Enquiry> findByOrganizationIdAndCreatedByIdOrderByCreatedDesc(@Param("organizationId") ObjectId organizationId, @Param("createdById") ObjectId createdId, Pageable pageable);
    Page<Enquiry> findByOrganizationIdOrderByCreatedDesc(@Param("organizationId") ObjectId organizationId, Pageable pageable);
    Page<Enquiry> findByOrganizationId(@Param("organizationId") ObjectId organizationId, Pageable pageable);
    Page<Enquiry> findByOrganizationIdAndCreatedByIdAndCreatedBetweenOrderByCreatedDesc(@Param("organizationId") ObjectId organizationId, @Param("createdById") ObjectId createdId, @Param("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start, @Param("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end, Pageable pageable);
    Page<Enquiry> findByOrganizationIdAndCreatedBetweenOrderByCreatedDesc(@Param("organizationId") ObjectId organizationId, @Param("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start, @Param("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end, Pageable pageable);

}
