package com.feedback.feedback.service;

import com.feedback.feedback.model.User;
import com.feedback.feedback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User login(String email, String password){
        return repository.login(email,password);
    }

    public User findById(String id){
        return repository.findById(id).get();
    }
}
