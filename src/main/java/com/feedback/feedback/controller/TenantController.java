package com.feedback.feedback.controller;

import com.feedback.feedback.config.JwtTokenProvider;
import com.feedback.feedback.dto.FeedbackCreateDto;
import com.feedback.feedback.dto.FeedbackUpdateDto;
import com.feedback.feedback.dto.StatisticsDto;
import com.feedback.feedback.facade.FeedbackFacade;
import com.feedback.feedback.model.Statistics;
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
            value = "/create",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveFeedback(@RequestBody FeedbackCreateDto dto, @RequestHeader(name = "Token") String token) {
        try {
            String tenant = jwtTokenProvider.getUsername(token);
            String createdId = facade.create(dto, tenant);
            return new ResponseEntity<>(createdId, HttpStatus.OK);
        } catch (SignatureException exception) {
            return new ResponseEntity<>("Invalid token!", HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping(
            value = "/delete/{feedbackId}"
    )
    public ResponseEntity<Void> deleteFeedback(@PathVariable("feedbackId") String feedbackId, @RequestHeader(name = "Token") String token) {
        String tenant = jwtTokenProvider.getUsername(token);
        facade.delete(feedbackId, tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(
            value = "/vote/{feedbackId}"
    )
    public ResponseEntity<Void> voteFeedback(@PathVariable("feedbackId") String feedbackId, @RequestHeader(name = "Token") String token) {
        String tenant = jwtTokenProvider.getUsername(token);
        facade.updateVote(feedbackId, 1L, tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(
            value = "/unvote/{feedbackId}")
    public ResponseEntity<Void> unvoteFeedback(@PathVariable String feedbackId, @RequestHeader(name = "Token") String token) {
        String tenant = jwtTokenProvider.getUsername(token);
        facade.updateVote(feedbackId, -1L, tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(
            value = "/update",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateFeedback(@RequestBody FeedbackUpdateDto dto, @RequestHeader(name = "Token") String token){
        String tenant = jwtTokenProvider.getUsername(token);
        facade.updateFeedback(dto,tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(
            value = "/statistics",
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Statistics> getStatistics(@RequestParam Long dateFrom, @RequestParam Long dateTo,
                                                    @RequestParam(required = false) String productCode,
                                                    @RequestHeader(name = "Token") String token){
        String tenant = jwtTokenProvider.getUsername(token);
        Statistics stats = facade.getStatistics(dateFrom,dateTo,productCode,tenant);
        if(stats!=null){
            return new ResponseEntity<>(stats,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
