package com.example.dto.user;

import com.example.dto.task.TaskDto;
import com.example.validation.LocalDateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Transitive table representation. Used for business logic")
public class MainTaskUserDto {

    private TaskDto task;

    private boolean isCreator;

    @NotNull(message = "{user.task.creation.null}")
    @NotBlank(message = "{user.task.creation.blank}")
    @LocalDateType
    private String participationDate;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9 a-zA-z]{15,150}$")
    @ApiModelProperty(example = "Some user`s comment", notes = "Latin and numeric characters. Minimum: 15, maximum: 150."
            + " Field which contains user`s suggestions concerning his/her help")
    private String comment;

    @NotNull(message = "{user.task.approved.null}")
    @ApiModelProperty(example = "false", notes = "Field to find out whether user is approved for task by task`s creator")
    private boolean approved;

}
