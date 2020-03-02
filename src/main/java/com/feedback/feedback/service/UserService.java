package com.feedback.feedback.service;

import com.feedback.feedback.model.User;
import com.feedback.feedback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User login(String email, String password){
        return repository.login(email,password);
    }
}
