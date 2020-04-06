package com.feedback.feedback.service;

import com.feedback.feedback.model.Feedback;

public interface FeedbackService {

    String save(Feedback feedback,String tenant);
    void vote(String feedbackId, String tenant);
    void unvote(String feedbackId, String tenant);
    void delete(String feedbackId, String tenant);

    //TODO: statistics
}
