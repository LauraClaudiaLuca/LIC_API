package com.feedback.feedback.controller;

import com.feedback.feedback.config.JwtTokenProvider;
import com.feedback.feedback.dto.FeedbackSaveDto;
import com.feedback.feedback.facade.FeedbackFacade;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private FeedbackFacade facade;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(
            value = "/save",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveFeedback(@RequestBody FeedbackSaveDto dto, @RequestHeader (name="Token") String token) {
        try{
            String tenant = jwtTokenProvider.getUsername(token);
            String createdId = facade.save(dto, tenant);
            return new ResponseEntity<>(createdId, HttpStatus.OK);
        }
        catch (SignatureException exception){
            return new ResponseEntity<>("Invalid token!",HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping(
            value = "/delete/feedbackID",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteFeedback(@PathVariable String feedbackId, @RequestHeader (name="Token") String token) {
        String tenant = jwtTokenProvider.getUsername(token);
        facade.delete(feedbackId,tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> voteFeedback(@PathVariable String feedbackId, @RequestHeader (name="Token") String token){
        String tenant = jwtTokenProvider.getUsername(token);
        facade.vote(feedbackId, tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> unvoteFeedback(@PathVariable String feedbackId, @RequestHeader (name="Token") String token){
        String tenant = jwtTokenProvider.getUsername(token);
        facade.unvote(feedbackId, tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
