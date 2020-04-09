package com.feedback.feedback.facade.impl;

import com.feedback.feedback.dto.LoginDto;
import com.feedback.feedback.dto.RegisterDto;
import com.feedback.feedback.facade.UserFacade;
import com.feedback.feedback.model.User;
import com.feedback.feedback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public User login(LoginDto dto) {
        return userService.login(dto.getUsername(), dto.getPassword());
    }

    @Override
    public boolean register(RegisterDto dto) {
        User user = new User(dto.getUsername(),dto.getPassword(), "USER",dto.getEmail());
        return userService.register(user);
    }
}
