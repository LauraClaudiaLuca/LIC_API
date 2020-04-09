package com.feedback.feedback.service.impl;

import com.feedback.feedback.model.User;
import com.feedback.feedback.repository.user.UserRepository;
import com.feedback.feedback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    public User login(String username, String password) {
        return repository.login(username, password);
    }

    public User findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean register(User user) {
        Optional<User> sameUsername = repository.findById(user.getUsername());
        if(!sameUsername.isPresent()){
            repository.save(user);
            return true;
        }
        return false;
    }
}
