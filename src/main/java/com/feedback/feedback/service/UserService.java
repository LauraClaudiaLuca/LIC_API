package com.feedback.feedback.service;

import com.feedback.feedback.model.User;

public interface UserService {
    User login(String username, String password);

    User findById(String id);

    boolean register(User user);
}
