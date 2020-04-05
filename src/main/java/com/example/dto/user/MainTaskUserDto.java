package com.example.dto.user;

import com.example.dto.task.MainTaskDto;
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

    private MainTaskDto task;

    private String participationDate;

    private String comment;

    @NotNull(message = "{user.task.approved.null}")
    @ApiModelProperty(example = "false", notes = "Field to find out whether user is approved for task by task`s creator")
    private boolean approved;

}
