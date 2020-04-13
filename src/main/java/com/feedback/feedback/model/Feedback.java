package com.feedback.feedback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @Indexed
    private String _id;

    @Field("title")
    private String title;

    @Field("content")
    private String content;

    @Indexed
    @Field("productCode")
    private String productCode;

    @Field("score")
    private Double score;

    @Field("likes")
    private Long likes;

    @Field("createdAt")
    private Long createdAt;
}
