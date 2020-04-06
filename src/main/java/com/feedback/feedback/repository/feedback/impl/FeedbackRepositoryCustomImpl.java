package com.feedback.feedback.repository.feedback.impl;

import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.repository.feedback.FeedbackRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class FeedbackRepositoryCustomImpl implements FeedbackRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String save(Feedback feedback, String tenant) {
        return null;
    }

    @Override
    public void delete(String id, String tenant) {

    }

    @Override
    public Feedback findById(String id, String tenant) {
        return null;
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
}
