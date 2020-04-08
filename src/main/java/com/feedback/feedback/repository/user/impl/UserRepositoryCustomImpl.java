package com.feedback.feedback.repository.user.impl;

import com.feedback.feedback.model.User;
import com.feedback.feedback.repository.user.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User login(String username, String password) {
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("_id").is(username),
                Criteria.where("password").is(password)));
        return mongoTemplate.findOne(query, User.class);
    }
}
