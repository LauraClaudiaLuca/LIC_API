package com.feedback.feedback.repository.feedback;

import com.feedback.feedback.model.Feedback;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackRepositoryCustom {

    String save(Feedback feedback, String tenant);

    void delete(String id, String tenant);

    List<Feedback> getFeedbackInTimeframe(LocalDateTime from, LocalDateTime to);

    List<Feedback> getFeedbackInTimeframe(LocalDateTime from, LocalDateTime to, String productCode);

    void updateVote(String id, Long value, String tenant);

    Feedback findById(String id, String tenant);
}
