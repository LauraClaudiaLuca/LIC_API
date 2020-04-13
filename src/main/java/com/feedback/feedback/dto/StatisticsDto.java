package com.feedback.feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDto {

    private String productCode;
    private Long dateFrom;
    private Long dateTo;
}
