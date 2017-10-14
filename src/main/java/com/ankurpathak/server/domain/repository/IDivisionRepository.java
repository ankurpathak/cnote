package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.Category;
import com.ankurpathak.server.domain.model.Division;
import com.ankurpathak.server.domain.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ankur on 25-02-2017.
 */
@Repository
public interface IDivisionRepository extends MongoRepository<Division, ObjectId> {
    Page<Division> findByOrganizationId(@Param("organizationId") ObjectId organizationId, Pageable pageable);
}
