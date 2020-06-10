package com.feedback.feedback.facade;

import com.feedback.feedback.dto.FeedbackCreateDto;
import com.feedback.feedback.dto.FeedbackUpdateDto;
import com.feedback.feedback.model.Statistics;

public interface FeedbackFacade {

    String create(FeedbackCreateDto dto, String token);

    boolean delete(String feedbackId, String token);

    boolean updateVote(String feedbackId,Long value, String token);

    boolean updateFeedback(FeedbackUpdateDto dto, String token);

    Statistics getStatistics(Long dateFrom, Long dateTo, String productCode, String token);

    Statistics getStatisticsNoLikes(Long dateFrom, Long dateTo, String productCode, String token);
}
