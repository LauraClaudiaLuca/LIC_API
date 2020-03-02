package com.feedback.feedback.repository;

import com.feedback.feedback.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
}
