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
@ApiModel(value = "FeedbackUpdateDto")
public class FeedbackUpdateDto {

    @NotNull
    @ApiModelProperty(value = "Id of the feedback given after add is performed.", required = true, example = "5e941c77cf26b62e6abfc3a1")
    private String id;

    @NotNull
    @ApiModelProperty(value = "Title of the feedback.", required = true, example = "Amazing cake!")
    private String title;

    @NotNull
    @ApiModelProperty(value = "Content of the feedback.", required = true, example = "The cake was delicious !")
    private String content;
}
