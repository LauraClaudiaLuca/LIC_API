package com.feedback.feedback.movies;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({ "timestamp", "commentText", "likes", "hasReplies","numberOfReplies" })
public class Movie {
    private String timestamp;
    private String commentText;
    private int likes;
    private String hasReplies;
    private int numberOfReplies;
}
