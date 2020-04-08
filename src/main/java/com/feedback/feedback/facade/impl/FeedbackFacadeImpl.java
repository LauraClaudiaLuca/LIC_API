package com.feedback.feedback.facade.impl;

import com.feedback.feedback.dto.FeedbackCreateDto;
import com.feedback.feedback.dto.FeedbackUpdateDto;
import com.feedback.feedback.facade.FeedbackFacade;
import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FeedbackFacadeImpl implements FeedbackFacade {

    @Autowired
    private FeedbackService service;

    @Override
    public String create(FeedbackCreateDto dto, String tenant) {
        return service.save(
                new Feedback(null, dto.getTitle(), dto.getContent(), dto.getProductCode(), null, 0L, LocalDateTime.now()),
                tenant);
    }

    @Override
    public void delete(String feedbackId, String tenant) {
        service.delete(feedbackId,tenant);
    }

    @Override
    public void updateVote(String feedbackId, Long value, String tenant) {
        service.updateVote(feedbackId, value, tenant);
    }

    @Override
    public void updateFeedback(FeedbackUpdateDto dto, String tenant) {
        Feedback old= service.findById(dto.getId(),tenant);
        service.save(
                new Feedback(dto.getId(),dto.getTitle(),dto.getContent(),old.getProductCode(),null,old.getLikes(),old.getCreatedAt()),
                tenant
        );
    }
}
