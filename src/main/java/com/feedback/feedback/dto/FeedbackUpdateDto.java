package com.feedback.feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackUpdateDto {

    @NotNull
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String content;
}
