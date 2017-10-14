package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.Category;
import com.ankurpathak.server.domain.model.SubCategory;
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
public interface ISubCategoryRepository extends MongoRepository<SubCategory, ObjectId> {
    Page<SubCategory> findByOrganizationId(@Param("organizationId") ObjectId organizationId, Pageable pageable);
    Page<SubCategory> findByOrganizationIdAndCategoryId(@Param("organizationId") ObjectId organizationId, @Param("categoryId") ObjectId categoryId, Pageable pageable);

}
