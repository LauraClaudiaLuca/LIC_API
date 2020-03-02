package com.feedback.feedback.repository.impl;

import com.feedback.feedback.model.User;
import com.feedback.feedback.repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User login(String email, String password) {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("email").is(email),
                Criteria.where("password").is(password)));
        return mongoTemplate.findOne(query, User.class);
    }
}
