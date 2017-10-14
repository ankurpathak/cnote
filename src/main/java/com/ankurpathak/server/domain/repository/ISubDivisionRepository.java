package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.Category;
import com.ankurpathak.server.domain.model.Division;
import com.ankurpathak.server.domain.model.SubDivision;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ankur on 25-02-2017.
 */
@Repository
public interface ISubDivisionRepository extends MongoRepository<SubDivision, ObjectId> {
    Page<SubDivision> findByOrganizationId(@Param("organizationId") ObjectId organizationId, Pageable pageable);
    Page<SubDivision> findByOrganizationIdAndDivisionId(@Param("organizationId") ObjectId organizationId, @Param("divisionId") ObjectId divisionId, Pageable pageable);

}
