package com.feedback.feedback.adapter.impl;

import com.feedback.feedback.adapter.AnalysisAdapter;
import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.npl_integrator.GoogleNLPIntegrator;
import com.google.cloud.language.v1.Sentiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnalysisAdapterImpl implements AnalysisAdapter {

    @Autowired
    private GoogleNLPIntegrator sentimentAnalyzer;

    @Override
    public double analyze(Feedback feedback) {
        Sentiment sentiment = sentimentAnalyzer.analyze(feedback.getContent());
        return sentiment.getScore();
    }
}
