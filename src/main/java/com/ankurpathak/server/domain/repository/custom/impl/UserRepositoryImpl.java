package com.ankurpathak.server.domain.repository.custom.impl;

import com.ankurpathak.server.domain.repository.custom.IUserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ankur on 05-02-2017.
 */
@Component("iUserRepositoryImpl")
public class UserRepositoryImpl implements IUserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;


    /*

    @Override
    public User byEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public List<User> byFirstName(String firstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> byFirstNameEndingWith(String firstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").regex(firstName+"$"));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> byFirstNameStartingWith(String firstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").regex("^"+firstName));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> byFirstNameContaining(String firstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").regex(firstName));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> byAgeBetween(BigInteger lower, BigInteger higher) {
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gte(lower).lte(higher));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> byOrderAgeAsc() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "age"));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> byPage(BigInteger pageNo, BigInteger pageSize) {
        Query query = new Query();
        query.with(new PageRequest(pageNo.intValue(), pageSize.intValue()));
        return mongoTemplate.find(query, User.class);
    }


 */

}
