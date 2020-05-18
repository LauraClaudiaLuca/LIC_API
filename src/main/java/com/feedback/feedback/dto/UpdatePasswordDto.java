package com.feedback.feedback.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UpdatePasswordDto")
public class UpdatePasswordDto {
    @ApiModelProperty(value = "username", required = true, example = "test")
    private String username;
    @ApiModelProperty(value = "Password.", required = true, example = "*****")
    private String password;
}