package com.feedback.feedback.facade;

import com.feedback.feedback.dto.FeedbackSaveDto;

public interface FeedbackFacade {

    String save(FeedbackSaveDto feedbackSaveDTO, String tenant);

    void delete(String feedbackId, String tenant);

    void vote(String feedbackId, String tenant);

    void unvote(String feedbackId, String tenant);
}
