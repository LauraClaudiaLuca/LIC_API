package com.feedback.feedback.controller;

import com.feedback.feedback.config.JwtTokenProvider;
import com.feedback.feedback.dto.LoginDto;
import com.feedback.feedback.model.User;
import com.feedback.feedback.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/login",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        try {
            Gson gson = new Gson();
            User user = userService.login(loginDto.getUsername(), loginDto.getPassword());
            String token = jwtTokenProvider.createToken(user);
            String tokenJson = gson.toJson(token);
            return new ResponseEntity<>(tokenJson, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied.");
        }
    }
}
