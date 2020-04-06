package com.feedback.feedback.facade.impl;

import com.feedback.feedback.dto.FeedbackSaveDto;
import com.feedback.feedback.facade.FeedbackFacade;
import org.springframework.stereotype.Component;

@Component
public class FeedbackFacadeImpl implements FeedbackFacade {
    @Override
    public String save(FeedbackSaveDto feedbackSaveDTO, String tenant) {
        return null;
    }

    @Override
    public void delete(String feedbackId, String tenant) {

    }

    @Override
    public void vote(String feedbackId, String tenant) {

    }

    @Override
    public void unvote(String feedbackId, String tenant) {

    }
}
