package com.feedback.feedback.repository.feedback;

import com.feedback.feedback.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String>, FeedbackRepositoryCustom {
}
