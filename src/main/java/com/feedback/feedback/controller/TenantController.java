package com.feedback.feedback.controller;

import com.feedback.feedback.config.JwtTokenProvider;
import com.feedback.feedback.dto.FeedbackCreateDto;
import com.feedback.feedback.dto.FeedbackUpdateDto;
import com.feedback.feedback.facade.FeedbackFacade;
import com.feedback.feedback.model.Statistics;
import io.jsonwebtoken.SignatureException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Api(tags = "Tenant API")
@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private FeedbackFacade facade;


    //    @ApiOperation(value = "Feedback is sent to be stored on INDIGO's servers.")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Feedback successfully stored."),
//            @ApiResponse(code = 403, message = "The provided token is invalid.")
//    })
    @PostMapping(
            value = "/create",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveFeedback(
            @RequestBody FeedbackCreateDto dto,
            @RequestHeader(name = "Token") String token) {
        try {
            String createdId = facade.create(dto, token);
            return new ResponseEntity<>(createdId, HttpStatus.OK);
        } catch (SignatureException exception) {
            return new ResponseEntity<>("Token not valid!", HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping(
            value = "/delete/{feedbackId}"
    )
    public ResponseEntity<Void> deleteFeedback(@PathVariable("feedbackId") String feedbackId, @RequestHeader(name = "Token") String token) {
        try {
            if (facade.delete(feedbackId, token)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (SignatureException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(
            value = "/vote/{feedbackId}"
    )
    public ResponseEntity<Void> voteFeedback(@PathVariable("feedbackId") String feedbackId, @RequestHeader(name = "Token") String token) {
        try {
            if (facade.updateVote(feedbackId, 1L, token)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (SignatureException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(
            value = "/unvote/{feedbackId}")
    public ResponseEntity<Void> unvoteFeedback(@PathVariable String feedbackId, @RequestHeader(name = "Token") String token) {
        try {
            if (facade.updateVote(feedbackId, -1L, token)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (SignatureException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(
            value = "/update",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> updateFeedback(@RequestBody FeedbackUpdateDto dto, @RequestHeader(name = "Token") String token) {
        try {
            if (facade.updateFeedback(dto, token)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (SignatureException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(
            value = "/statistics",
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Statistics> getStatistics(@RequestParam Long dateFrom, @RequestParam Long dateTo,
                                                    @RequestParam(required = false) String productCode,
                                                    @RequestHeader(name = "Token") String token) {
        try {
            Statistics stats = facade.getStatistics(dateFrom, dateTo, productCode, token);
            if (stats != null) {
                return new ResponseEntity<>(stats, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SignatureException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
