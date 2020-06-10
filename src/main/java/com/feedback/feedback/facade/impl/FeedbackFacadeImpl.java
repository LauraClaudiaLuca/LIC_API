package com.feedback.feedback.facade.impl;

import com.feedback.feedback.config.JwtTokenProvider;
import com.feedback.feedback.dto.FeedbackCreateDto;
import com.feedback.feedback.dto.FeedbackUpdateDto;
import com.feedback.feedback.facade.FeedbackFacade;
import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.model.Statistics;
import com.feedback.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class FeedbackFacadeImpl implements FeedbackFacade {

    @Autowired
    private FeedbackService service;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String create(FeedbackCreateDto dto, String token) {
        String tenant = jwtTokenProvider.getUsername(token);
        return service.save(
                new Feedback(null, dto.getTitle(), dto.getContent(), dto.getProductCode(), null, 0L, Instant.now().toEpochMilli()),
                tenant);
    }

    @Override
    public boolean delete(String feedbackId, String tenant){
    return service.delete(feedbackId,tenant);
    }

    @Override
    public boolean updateVote(String feedbackId, Long value, String token) {
        String tenant = jwtTokenProvider.getUsername(token);
        return service.updateVote(feedbackId, value, tenant);
    }

    @Override
    public boolean updateFeedback(FeedbackUpdateDto dto, String token) {
        String tenant = jwtTokenProvider.getUsername(token);
        Feedback old= service.findById(dto.getId(),tenant);
        if(service.findById(dto.getId(),tenant)!=null) {
            service.save(
                    new Feedback(dto.getId(), dto.getTitle(), dto.getContent(), old.getProductCode(), null, old.getLikes(), old.getCreatedAt()),
                    tenant
            );
            return true;
        }
        return false;
    }

    @Override
    public Statistics getStatistics(Long dateFrom, Long dateTo, String productCode, String token) {
        String tenant = jwtTokenProvider.getUsername(token);
        return service.getStatistics(productCode,dateFrom,dateTo,tenant);

    }

    @Override
    public Statistics getStatisticsNoLikes(Long dateFrom, Long dateTo, String productCode, String token) {
        String tenant = jwtTokenProvider.getUsername(token);
        return service.getStatisticsNoLikes(productCode,dateFrom,dateTo,tenant);
    }

}
