package com.feedback.feedback.facade;

import com.feedback.feedback.dto.FeedbackCreateDto;
import com.feedback.feedback.dto.FeedbackUpdateDto;

public interface FeedbackFacade {

    String create(FeedbackCreateDto dto, String tenant);

    void delete(String feedbackId, String tenant);

    void updateVote(String feedbackId,Long value, String tenant);

    void updateFeedback(FeedbackUpdateDto dto, String tenant);

}
