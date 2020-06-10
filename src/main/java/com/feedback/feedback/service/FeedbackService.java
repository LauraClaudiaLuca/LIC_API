package com.feedback.feedback.service;

import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.model.Statistics;

import java.time.LocalDateTime;

public interface FeedbackService {

    String save(Feedback feedback, String tenant);

    boolean updateVote(String feedbackId, Long value, String tenant);

    boolean delete(String feedbackId, String tenant);

    Statistics getStatistics(String productCode, Long from, Long to, String tenant);

    Statistics getStatisticsNoLikes(String productCode, Long from, Long to, String tenant);

    Feedback findById(String id, String tenant);
}
