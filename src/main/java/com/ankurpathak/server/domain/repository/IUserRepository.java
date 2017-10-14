package com.ankurpathak.server.domain.repository;

import com.ankurpathak.server.domain.model.User;
import com.ankurpathak.server.domain.model.projection.UserOrganizationProjection;
import com.ankurpathak.server.domain.repository.custom.IUserRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by ankur on 05-02-2017.
 */
@Repository
@RepositoryRestResource(excerptProjection = UserOrganizationProjection.class)
public interface IUserRepository extends MongoRepository<User, ObjectId>,  IUserRepositoryCustom {
    Optional<User> findByEmail(@Param("email") String email);
    Optional<User> findByUsername(@Param("username") String username);
    Optional<User> findByContact(@Param("contact") String contact);
    //@Query("{organizationId : ?0 , roles : ?1 }")
    List<User> findByOrganizationId(@Param("organizationId") ObjectId organizationId);
    List<User> findByOrganizationIdAndRoles(@Param("organizationId") ObjectId organizationId, @Param("roles") User.Role role);
}
