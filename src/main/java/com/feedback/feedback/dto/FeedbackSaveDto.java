package com.feedback.feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackSaveDto {

    private String title;
    private String content;
    private String productCode;
    private String score;
    private Long likes;

}
