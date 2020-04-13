package com.feedback.feedback.service.impl;

import com.feedback.feedback.adapter.AnalysisAdapter;
import com.feedback.feedback.model.Feedback;
import com.feedback.feedback.model.Statistics;
import com.feedback.feedback.repository.feedback.FeedbackRepository;
import com.feedback.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private AnalysisAdapter analysisAdapter;

    @Value("${negative.start}")
    private double negativeStart;
    @Value("${negative.end}")
    private double negativeEnd;
    @Value("${neutral.start}")
    private double neutralStart;
    @Value("${neutral.end}")
    private double neutralEnd;
    @Value("${positive.start}")
    private double positiveStart;
    @Value("${positive.end}")
    private double positiveEnd;

    @Override
    public String save(Feedback feedback, String tenant) {
        double score = analysisAdapter.analyze(feedback);
        feedback.setScore(score);
        return repository.save(feedback, tenant);
    }

    @Override
    public void updateVote(String feedbackId, Long value, String tenant) {
        repository.updateVote(feedbackId, value, tenant);
    }

    @Override
    public void delete(String feedbackId, String tenant) {
        repository.delete(feedbackId, tenant);
    }

    @Override
    public Statistics getStatistics(String productCode, Long from, Long to, String tenant) {
        List<Feedback> feedbacks;
        if (productCode == null) {
            feedbacks = repository.getFeedbackInTimeframe(from, to, tenant);
        }
        else{
            feedbacks = repository.getFeedbackInTimeframe(from, to, productCode, tenant);
        }
        if (!feedbacks.isEmpty()) {
            Optional<Long> nrNegative = feedbacks.stream()
                    .filter(f -> f.getScore() >= negativeStart && f.getScore() < negativeEnd)
                    .map(f -> f.getLikes() + 1)
                    .reduce((acc, item) -> acc + item);
            Optional<Long> nrNeutral = feedbacks.stream()
                    .filter(f -> f.getScore() >= neutralStart && f.getScore() <= neutralEnd)
                    .map(f -> f.getLikes() + 1)
                    .reduce((acc, item) -> acc + item);
            Optional<Long> nrPositive = feedbacks.stream()
                    .filter(f -> f.getScore() > positiveStart && f.getScore() <= positiveEnd)
                    .map(f -> f.getLikes() + 1)
                    .reduce((acc, item) -> acc + item);
            double negative = nrNegative.isPresent()?nrNegative.get():0;
            double positive=nrPositive.isPresent()?nrPositive.get():0;
            double neutral = nrNeutral.isPresent()?nrNeutral.get():0;

            double total = negative + positive+ neutral;
            double percNegative = calculatePercentage(negative, total);
            double percNeutral = calculatePercentage(neutral, total);
            double percPositive = calculatePercentage(positive, total);

            feedbacks.sort(Comparator.comparing(Feedback::getScore));
            Feedback mostPositive = feedbacks.get(feedbacks.size() - 1);
            Feedback mostNegative = feedbacks.get(0);
            feedbacks.sort(Comparator.comparing(Feedback::getLikes));
            Feedback mostLiked = feedbacks.get(feedbacks.size() - 1);

            return new Statistics(percPositive, percNegative, percNeutral, mostPositive, mostNegative, mostLiked);
        }
        return null;
    }

    @Override
    public Feedback findById(String id, String tenant) {
        return repository.findById(id, tenant);
    }

    private double calculatePercentage(double obtained, double total) {
        return round(obtained * 100 / total);
    }

    private static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
