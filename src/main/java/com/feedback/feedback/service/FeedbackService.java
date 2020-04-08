package com.feedback.feedback.service;

import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.model.Statistics;

import java.time.LocalDateTime;

public interface FeedbackService {

    String save(Feedback feedback, String tenant);

    void updateVote(String feedbackId, Long value, String tenant);

    void delete(String feedbackId, String tenant);

    Statistics getStatistics(String productCode, LocalDateTime from, LocalDateTime to, String tenant);

    Feedback findById(String id, String tenant);
}
