package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.Category;
import com.ankurpathak.server.domain.model.Product;
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
public interface IProductRepository extends MongoRepository<Product, ObjectId> {
    Page<Product> findByOrganizationId(@Param("organizationId") ObjectId organizationId, Pageable pageable);
    Page<Product> findByOrganizationIdAndDivisionId(@Param("organizationId") ObjectId organizationId, @Param("divisionId") ObjectId divisionId, Pageable pageable);
    Page<Product> findByOrganizationIdAndSubDivisionId(@Param("organizationId") ObjectId organizationId,@Param("subDivisionId") ObjectId subDivisionId, Pageable pageable);
    Page<Product> findByOrganizationIdAndCategoryId(@Param("organizationId") ObjectId organizationId,@Param("categoryId") ObjectId categoryId, Pageable pageable);
    Page<Product> findByOrganizationIdAndSubCategoryId(@Param("organizationId") ObjectId organizationId,@Param("subCategoryId") ObjectId subCategoryId, Pageable pageable);

    Page<Product> findByOrganizationIdAndNameContainingIgnoreCase(@Param("organizationId") ObjectId organizationId, @Param("name") String name,Pageable pageable);
    Page<Product> findByOrganizationIdAndDivisionIdAndNameContainingIgnoreCase(@Param("organizationId") ObjectId organizationId, @Param("divisionId") ObjectId divisionId, @Param("name") String name, Pageable pageable);
    Page<Product> findByOrganizationIdAndSubDivisionIdAndNameContainingIgnoreCase(@Param("organizationId") ObjectId organizationId,@Param("subDivisionId") ObjectId subDivisionId, @Param("name") String name, Pageable pageable);
    Page<Product> findByOrganizationIdAndCategoryIdAndNameContainingIgnoreCase(@Param("organizationId") ObjectId organizationId,@Param("categoryId") ObjectId categoryId, @Param("name") String name, Pageable pageable);
    Page<Product> findByOrganizationIdAndSubCategoryIdAndNameContainingIgnoreCase(@Param("organizationId") ObjectId organizationId,@Param("subCategoryId") ObjectId subCategoryId, @Param("name") String name, Pageable pageable);


}
