package com.feedback.feedback.service.impl;

import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.repository.feedback.FeedbackRepository;
import com.feedback.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    @Override
    public String save(Feedback feedback, String tenant) {
        return null;
    }

    @Override
    public void vote(String feedbackId, String tenant) {

    }

    @Override
    public void unvote(String feedbackId, String tenant) {

    }

    @Override
    public void delete(String feedbackId, String tenant) {

    }
}
