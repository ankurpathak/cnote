package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by ankur on 12-03-2017.
 */
@Repository
public interface IClientRepository extends MongoRepository<Client, ObjectId> {
    Optional<Client> findByClientId(@Param("clientId") String clientId);
}
