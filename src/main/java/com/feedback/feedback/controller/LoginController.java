package com.feedback.feedback.controller;

import com.feedback.feedback.dto.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class LoginController {

    @PostMapping(value = "/login",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
//        try {
//            Gson gson = new Gson();
//            UserEntity user = authService.findUser(auth);
//            String token = jwtTokenProvider.createToken(user);
//            String tokenJson = gson.toJson(token);
//            return new ResponseEntity<>(tokenJson, HttpStatus.ACCEPTED);
//        } catch (Exception e) {
//            throw new BadCredentialsException("Invalid username/password supplied.");
//        }
        return null;
    }
}
