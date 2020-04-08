package com.feedback.feedback.repository.feedback.impl;

import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.repository.feedback.FeedbackRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;
import java.util.List;

public class FeedbackRepositoryCustomImpl implements FeedbackRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String save(Feedback feedback, String tenant) {
        return mongoTemplate.save(feedback, tenant).get_id();
    }

    @Override
    public void delete(String id, String tenant) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), tenant);
    }

    @Override
    public List<Feedback> getFeedbackInTimeframe(LocalDateTime from, LocalDateTime to) {
        //TODO: implement
        return null;
    }

    @Override
    public List<Feedback> getFeedbackInTimeframe(LocalDateTime from, LocalDateTime to, String productCode) {
        //TODO: implement
        return null;
    }

    @Override
    public void updateVote(String id, Long value, String tenant) {
        Update update = new Update().inc("likes", value);
        mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(id)), update, tenant);
    }

    @Override
    public Feedback findById(String id, String tenant) {
        return mongoTemplate.findById(id, Feedback.class, tenant);
    }
}
