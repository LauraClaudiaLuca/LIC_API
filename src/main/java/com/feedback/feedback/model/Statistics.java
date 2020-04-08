package com.feedback.feedback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {

    private Double positive;
    private Double negative;
    private Double neutral;

    private Feedback mostPositive;
    private Feedback mostNegative;
    private Feedback mostLiked;
}
