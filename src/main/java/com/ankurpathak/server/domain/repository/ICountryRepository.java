package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.Country;
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
public interface ICountryRepository extends MongoRepository<Country, ObjectId>{
    Page<Country> findAllByOrderByName(Pageable pageable);
    Page<Country> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}
