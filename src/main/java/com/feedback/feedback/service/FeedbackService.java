package com.feedback.feedback.service;

import com.feedback.feedback.model.Feedback;

public interface FeedbackService {

    String save(Feedback feedback, String tenant);

    void updateVote(String feedbackId, Long value, String tenant);

    void delete(String feedbackId, String tenant);

    void Statistics(String productCode, String tenant);

    Feedback findById(String id, String tenant);
}
