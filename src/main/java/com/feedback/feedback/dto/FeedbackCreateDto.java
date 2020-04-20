package com.feedback.feedback.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FeedbackCreateDto")
public class FeedbackCreateDto {
    @NotNull
    @ApiModelProperty(value = "Title of the feedback", required = true, example = "Amazing cake!")
    private String title;
    @NotNull
    @ApiModelProperty(value = "Content of the feedback", required = true, example = "The cake was delicious !")
    private String content;
    @ApiModelProperty(value = "Product code associated with the feedback.",  example = "10000")
    private String productCode;
}
