package com.feedback.feedback.service.impl;

import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.model.Statistics;
import com.feedback.feedback.repository.feedback.FeedbackRepository;
import com.feedback.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    @Override
    public String save(Feedback feedback, String tenant) {
        return repository.save(feedback,tenant);
    }

    @Override
    public void updateVote(String feedbackId,Long value, String tenant) {
        repository.updateVote(feedbackId,value,tenant);
    }

    @Override
    public void delete(String feedbackId, String tenant) {
        repository.delete(feedbackId,tenant);
    }

    @Override
    public Statistics getStatistics(String productCode, LocalDateTime from, LocalDateTime to, String tenant) {
        //TODO: implement statistics
        return null;
    }

    @Override
    public Feedback findById(String id, String tenant) {
        return repository.findById(id,tenant);
    }
}
